<template>
  <v-app id="inspire">
    <v-app-bar
        app
        color="white"
        flat
    >
      <v-container class="py-0 fill-height">

        <v-row class="ma-0">
          <v-col cols="1">
            <v-avatar
                class="mr-10"
                color="grey darken-1"
                size="32"
            ></v-avatar>
          </v-col>

          <v-col cols="2" >
            <v-btn
                v-for="(national,idx) in nationals"
                :key="idx"
                text
                @click="linkTo4(national.national)"
            >
              {{ national.national }}
            </v-btn>
          </v-col>

          <v-col cols="2">
            <v-select
                class="pl-8"
                label="검색조건"
                v-model="category"
                :items="keywords"
                item-text="name"
                item-value="id">
            </v-select>
          </v-col>

          <v-col cols="3">
            <v-responsive max-width="auto">
              <v-text-field
                  dense
                  flat
                  hide-details
                  rounded
                  solo-inverted
                  v-model="carName"
              ></v-text-field>

            </v-responsive>
          </v-col>

          <v-col cols="1">
            <v-btn icon>
              <v-icon

              >mdi-magnify</v-icon>
            </v-btn>

          </v-col>

          <v-col cols="3" >
            <v-btn
                v-for="(individual,idx) in individuals"
                :key="idx"
                @click="linkTo(individual.link)"
                text
            >
              {{ individual.individual }}
            </v-btn>
          </v-col>

        </v-row>

        <v-spacer></v-spacer>


        <v-row
            align="center"
            justify="space-around">
          <v-col cols=12
                 md="6"
                 class="">

            <v-btn
                tile
                color="primary"
                @click="linkTo"
            >

              뒤로가기</v-btn>
          </v-col>

          <v-col cols="12"
                 md="6">
            <v-btn
                tile
                color="success"
                @click="linkTo5"
            >
              <v-icon
                  left>
                mdi-home
              </v-icon>
              홈화면</v-btn>
          </v-col>

        </v-row>

      </v-container>
      </v-app-bar>

    <v-main class="grey lighten-3">
      <v-container>
        <v-row>
          <v-col cols="12">


            <v-row justify="center">
              <v-card width="1000"  min-height="1000" height="auto">
                <v-col>
                  <div class="black--text ms-4 ma-4">
                    {{nickName}} 님의 위시리스트입니다.
                  </div>
                  <v-divider class="mx-4"></v-divider>


  <v-row class="" style="height: 100vh">
    <div v-show="dataMsg">위시리스트가 없습니다.</div>
    <div v-for="(data, index) in wishList"
         :key="index"
    >
      <v-col cols="12" class="ma-12">
            <v-card width="190" height="360">
              <v-img
                  :src="require('@/assets/thumb/' + data.thumb)"
              />

              <div class="pa-3">

                <router-link :to="{ name: 'CarDetail', query:{ carId: data.carId}}">
                  <span style="font-size: 14px">
                    {{data.carName}}
                  </span>
                </router-link>
              </div>

              <v-card-text>
                <v-row
                    align="center"
                    class="mx-0"
                >

                  <div class="black--text ms-4">
                    연식 : {{data.year}}
                  </div>
                </v-row>

                <v-row
                    align="center"
                    class="mx-0"
                >
                  <div class="black--text ms-4">
                    주행거리 : {{data.distance}}
                  </div>
                </v-row>

                <v-row
                    align="center"
                    class="mx-0"
                >
                  <div  class="black--text ms-4">지역 : {{data.area}}</div>
                </v-row>

                <v-row
                    align="center"
                    class="mx-0"
                >
                  <div  class="black--text ms-4">가격 : {{data.price}}</div>
                </v-row>
              </v-card-text>

              <v-divider class="mx-4"></v-divider>



              <v-card-actions>
                <v-spacer></v-spacer>
                <v-btn
                    color="deep-purple lighten-2"
                    text
                    @click="delWishCar(data.wid)"
                >
                  찜목록 삭제
                </v-btn>
              </v-card-actions>
            </v-card>
      </v-col>
    </div>
  </v-row>
                </v-col>
              </v-card>
            </v-row>
          </v-col>
        </v-row>
      </v-container>
    </v-main>
  </v-app>
</template>

<script>
export default {
  data() {
    return {
      nationals: [
        {national:'국산'},
        {national:'수입'}
      ],

      individuals: [
        {individual:'마이페이지', link:"MyPage"},
        {individual:'로그아웃', link:"Mypage"}
      ],

      carName: '',

      category:'',
      keywords:['작성자', '차량명','지역'],

    loading: false,
    selection: 1,
    wishList:[],
    wid:'',
    dataMsg : false,
      nickName : this.$store.state.userStore.nickName,

  }
  },

  methods: {

    linkTo() {
      this.$router.go(-1);
    },

    getWishList() {

      let mid = this.$store.state.userStore.mid;
      console.log(mid)
      this.$axios.get("wishlist/" + mid)
          .then(res => {
            this.wishList = res.data;
            console.log(res.data);
            console.log(this.wishList)
            this.dataMsg = res.data.length === 0;
          })
          .catch(e => {
            console.log(e);
          })
    },

    delWishCar(wid) {

      console.log('this.wid:' + wid)
      this.$axios.delete("wishlist/" + wid)
      alert("찜목록에서 삭제 되었습니다.");
      this.$router.go(0);
    }


  },

  mounted() {
    this.getWishList()
  }
}
</script>