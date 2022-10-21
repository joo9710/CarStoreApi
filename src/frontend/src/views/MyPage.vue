<template>
  <v-app id="inspire">
  <div class="grey darken-1">

    <v-app-bar
        app
        color="white"
        flat
    >
      <v-container class="py-0 fill-height" >

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
                @click="linkTo6"
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
        <v-row >
      <v-col cols="12">


    <v-row justify="center">
      <v-card width="1200"  min-height="800" height="auto">
        <v-col>
          <div class="black--text ms-4">
            {{nickName}} 님의 마이페이지입니다.
            <v-btn
                outlined
                rounded
                text
                @click="linkTo(link2)">회원정보 수정</v-btn>
          </div>
        </v-col>

        <v-divider class="mx-4"></v-divider>

        <v-row class="ma-0">
          <v-col cols="4" >
            <div class="black--text ms-4">
              나의 찜목록
              <v-btn
                  outlined
                  rounded
                  text
                  @click="linkTo3">자세히</v-btn>
            </div>

            <v-row class="ma-0" justify="center">
              <div v-if="dataMsg2">위시리스트가 없습니다.</div>
              <div v-for="(data, index) in wishList"
                   :key="index"
              >
                <v-col cols="12">
                  <v-card width="150" height="165" align="center">
                    <v-img
                        width="150px"
                        :src="require('@/assets/thumb/' + data.thumb)"
                    />


                    <div class="pl-1 pt-1">
                      <router-link :to="{ name: 'CarDetail', query:{ carId: data.carId}}">
                        {{data.subCarName}}
                      </router-link>
                    </div>
                    <div  class="black--text pl-1">{{data.price}}</div>

                  </v-card>
                </v-col>
              </div>
            </v-row>
          </v-col>

          <v-divider vertical>

          </v-divider>

          <v-col cols="8" >
            <div class="black--text ms-4" >
              판매중인 차량 | 총 {{count}}대
              <v-btn
                  outlined
                  rounded
                  text
                  @click="linkTo(link3)">전체 보기</v-btn>

            </div>

            <v-row class="ma-0" justify="center">
              <div v-if="dataMsg">판매중인 차량이 없습니다.</div>
              <div v-for="(data, index) in myCarList"
                   :key="index"
              >
                <v-col cols="12">
                  <v-card width="310" height="140" >
                    <v-list-item three-line>
                      <v-list-item-content>
                        <v-list-item-title class="text-h5 mb-1">
                          {{data.subCarName}}
                        </v-list-item-title>
                        <v-list-item-subtitle>
                          조회수: {{data.readCount}} |
                          댓글수: {{data.commentCount}}

                        </v-list-item-subtitle>
                      </v-list-item-content>

                      <v-card>
                        <v-img
                            width="110px"
                            :src="require('@/assets/thumb/' + data.thumb)"
                        />
                      </v-card>
                    </v-list-item>

                    <v-card-actions>
                      <v-btn
                          outlined
                          rounded
                          text
                          @click ="linkTo2(data.carId)"
                      >
                        상세보기
                      </v-btn>
                    </v-card-actions>


                  </v-card>
                </v-col>
              </div>
            </v-row>
          </v-col>


        </v-row>
      </v-card>
    </v-row>
      </v-col>
        </v-row>
      </v-container>
    </v-main>
  </div>


  </v-app>
</template>

<script>
export default {
  name: "MyPage.vue",

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

      link1 :'WishList',
      link2 :'putMember',
      link3 :'SaleOfMyCar',
      nickName : this.$store.state.userStore.nickName,

      wishList:[],
      myCarList:[],
      getCount:[],
      carIdList:[],

      count:0,
      dataMsg : true,
      dataMsg2 : true
    }
  },

  methods: {

    linkTo(data) {
      this.$router.push({name: data})
    },

    linkTo2(carId){
      this.$router.push({name:"CarDetail", query: { carId: carId }})
    },

    linkTo3() {
      if(this.dataMsg2===true) {
        alert("찜한 차량이 없습니다.");
      }else {
        this.$router.push({name:"WishList"});
      }
    },

    linkTo4(national){
      this.$router.push({name:"CarOfNational", query: {national: national, page: this.page -1, size: this.size}})
      this.$router.go(0);
    },

    linkTo5(){
      this.$router.push({name:"test2"})
    },

    linkTo6() {
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
            if(res.data.length === 0) {
              this.dataMsg2 = true
            } else
            {
              this.dataMsg2 = false
            }

          })
          .catch(e => {
            console.log(e);
          })
    },

    getCarCount() {

      let mid = this.$store.state.userStore.mid;
      this.$axios.get("car/carCount/" + mid)
      .then(res => {
        this.count = res.data;
      })
      .catch(e => {
        console.log(e);
      })
    },

    getMyCar() {

      let mid = this.$store.state.userStore.mid;
      this.$axios.get("car/myCar/" + mid)
      .then(res => {
        this.myCarList = res.data;
        console.log(res.data);
        if(res.data.length === 0) {
          this.dataMsg = true;
        }
        else {
          this.dataMsg = false;
        }

      })
          .catch(e => {
            console.log(e);
          })
    },

  },

  mounted() {
    this.getWishList();
    this.getCarCount();
    this.getMyCar();
  },
}
</script>

