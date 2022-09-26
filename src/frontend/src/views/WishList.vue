<template>
  <v-row class="blue" style="height: 100vh">
    <div v-show="dataMsg">위시리스트가 없습니다.</div>
    <div v-for="(data, index) in wishList"
         :key="index"
    >
      <v-col cols="12" class="ma-12">
            <v-card width="190" height="450px">
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
</template>

<script>
export default {
  data: () => ({
    loading: false,
    selection: 1,
    wishList:[],
    wid:'',
    dataMsg : false,
  }),

  methods: {
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