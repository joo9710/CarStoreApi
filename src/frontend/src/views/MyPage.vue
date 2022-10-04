<template>
  <div class="grey darken-1">
    <v-row justify="center">
      <v-card width="830px" height="100vh">
        <v-col>
          <div class="black--text ms-4">
            {{nickName}} 의 마이페이지입니다.
            <v-btn
                outlined
                rounded
                text
                @click="linkTo(link2)">회원정보 수정</v-btn>
          </div>
        </v-col>

        <v-divider class="mx-4"></v-divider>

        <v-row class="ma-0">
          <v-col cols="6" >
            <div class="black--text ms-4">
              나의 찜목록
              <v-btn
                  outlined
                  rounded
                  text
                  @click="linkTo(link1)">상세 보기</v-btn>
            </div>

            <v-row class="ma-0 red">
              <div v-show="dataMsg">위시리스트가 없습니다.</div>
              <div v-for="(data, index) in wishList"
                   :key="index"
              >
                <v-col cols="12">
                  <v-card width="110" height="135">
                    <v-img
                        width="110px"
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
          <v-col cols="6" class="blue">
            <div class="black--text ms-4">
              판매중인 차량 | 총 {{count}}대
              <v-btn
                  outlined
                  rounded
                  text
                  @click="linkTo(link1)">상세 보기</v-btn>

            </div>

            <v-row class="ma-0 red">
              <div v-show="dataMsg">판매중인 차량이 없습니다.</div>
              <div v-for="(data, index) in myCarList"
                   :key="index"
              >
                <v-col cols="12">
                  <v-card width="310" height="140">
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
  </div>
</template>

<script>
export default {
  name: "MyPage.vue",

  data() {
    return {
      link1 :'WishList',
      link2 :'putMember',
      nickName : this.$store.state.userStore.nickName,

      wishList:[],
      myCarList:[],
      getCount:[],
      carIdList:[],

      count:0,
      dataMsg : false,
      dataMsg2 : false
    }
  },

  methods: {

    linkTo(data) {
      this.$router.push({name: data})
    },

    linkTo2(carId){
      this.$router.push({name:"CarDetail", query: { carId: carId }})
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
        this.dataMsg2 = res.data.length === 0;
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

