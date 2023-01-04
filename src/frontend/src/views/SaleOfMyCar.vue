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
    <v-row>
      <v-col cols="1"></v-col>
      <v-col cols="10">
      <div>
        <v-data-table
            :headers="headers"
            :items="users"
            hide-default-footer
            class="elevation-1"
        >

          <template v-slot:item.thumb="{ item }">
            <v-img
                v-if="item.thumb"
                :src="require('@/assets/thumb/'+ item.thumb)"
                style="object-fit: cover"
                height="120px"
                width="180px"
                alt=""/>
          </template>

          <template v-slot:item.content="{ item }">
            <v-row>
              <v-col cols="12" class="pa-0">
                <router-link :to="{ name: 'CarDetail', query:{ carId: item.carId } }">
                  {{item.carName}}
                </router-link>

              </v-col>

              <v-col cols="12" class="pa-0">
                <span>{{item.year}} | </span>
                <span>{{item.distance}} | </span>
                <span>{{item.fuel}} | </span>
                <span>{{item.area}}</span>
              </v-col>
            </v-row>

          </template>
        </v-data-table>

        <template>
          <div class="text-center">
            <v-pagination
                v-model="page"
                :length="totalPage"
                prev-icon="mdi-menu-left"
                next-icon="mdi-menu-right"
                @input="retrieveUsers"
            ></v-pagination>
          </div>
        </template>

      </div>
      </v-col>
    </v-row>
      </v-container>
    </v-main>

    <v-footer
        color="white"
        padless
    >
      <v-row
          align="center"
          justify="center"
          no-gutters
      >

        <v-btn
            text
            rounded
            class="my-2"
            @click="linkTo5"
        >
          <v-icon>
            mdi-home
          </v-icon>

        </v-btn>
        <v-btn
            text
            rounded
            class="my-2"
            @click="linkTo8"
        >
          <v-icon>
            mdi-heart
          </v-icon>

        </v-btn>

        <v-btn
            :href="`https://github.com/joo9710/CarStoreApi`" target="_blank"
            text
            rounded
            class="my-2"
        >
          <v-icon>mdi-github</v-icon>
        </v-btn>

        <v-btn
            :href="`https://youtube.com`" target="_blank"
            text
            rounded
            class="my-2"
        >
          <v-icon>mdi-youtube</v-icon>
        </v-btn>

        <v-btn
            :href="`https://www.instagram.com/`" target="_blank"
            text
            rounded
            class="my-2"
        >

          <v-icon>mdi-instagram</v-icon>

        </v-btn>

        <v-btn
            :href="`https://z00h.tistory.com/`" target="_blank"
            text
            rounded
            class="my-2"
        >
          <span>Blog</span>
        </v-btn>

      </v-row>
    </v-footer>

  </v-app>
</template>

<script>
export default {
  name: "SaleOfMyCar.vue",

  data() {
    return {
      nationals: [
        {national:'국산'},
        {national:'수입'}
      ],

      individuals: [
        {individual:'마이페이지', link:"MyPage"}
      ],

      headers: [
        {text: '', value: 'thumb'},
        {text: '차량정보', value: 'content'},
        {text: '등록일', value: 'writeDate', align: 'center', sortable: false},
        {text: '가격', value: 'price', align: 'center', sortable: false},
      ],

      users:[],
      totalPage:0,
      page:1,
      size:5,

      mid:this.$store.state.userStore.mid,

    }
  },

  methods: {

    linkTo5(){
      this.$router.push({name:"Car"})
    },

    linkTo6() {
      this.$router.go(-1);
    },

    linkTo7() {
      this.$router.push({name:"CarSearch", query: {category: this.category, keyword: this.carName, page: this.page-1, size: this.size}})
    },

    linkTo8() {
      if(this.$store.state.userStore.mid ==0) {
        alert("로그인 후 사용 바랍니다.");
      }else {
        this.$router.push({name:"WishList"});
      }
    },


    retrieveUsers() {
      let data = {};
      data.mid = this.mid;
      data.page = this.page-1;
      data.size = this.size
      this.$axios.post("car/saleOfMyCar", JSON.stringify(data),{
        headers: {
          "Content-Type": `application/json`,
        },
      }).then(response => {
            this.totalPage = response.data.totalPages;
            this.users = response.data.content;
            console.log(response.data.content);
          })
    }
  },

  mounted() {
    this.retrieveUsers();
  },
}
</script>
