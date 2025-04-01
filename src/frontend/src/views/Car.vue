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
            <v-btn icon
            @click="linkTo7">
              <v-icon
              >mdi-magnify</v-icon>

            </v-btn>

          </v-col>

          <div v-show="wishBtnShow">
          <v-col cols="1" >
            <v-btn
                v-for="(individual,idx) in individuals1"
                :key="idx"
                @click="linkTo(individual.link)"
                text
            >
              {{ individual.individual }}
            </v-btn>
          </v-col>
          </div>

          <div v-show="wishBtnShow">
          <v-col cols="1" >
            <v-btn
                v-for="(individual,idx) in individuals2"
                :key="idx"
                @click="logout()"
                text
            >
              {{ individual.individual }}
            </v-btn>
          </v-col>
          </div>

          <div v-show="loginBtnShow">
            <v-col cols="1" >
              <v-btn
                  @click= "linkTo6()"
                  text
              >
              로그인</v-btn>
            </v-col>
          </div>

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
                @click="linkTo(link1)"
            >
              <v-icon left>
                mdi-pencil
              </v-icon>
              글쓰기</v-btn>
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
          <v-col cols="2">
            <v-sheet rounded="lg">
              <v-list color="transparent">
                <v-list-item>
                  <v-select
                      class="pl-8"
                      label="지역선택"
                      v-model="area"
                      :items="pickArea"
                      item-text="name"
                      item-value="id"

                  >
                  </v-select>
                </v-list-item>
                <v-list-item
                    v-for="(company,idx) in companies"
                    :key="idx"
                    link
                    @click="linkTo3(company.company)"
                >
                  <v-list-item-content>
                    <v-list-item-title>
                      {{company.company}}
                    </v-list-item-title>
                  </v-list-item-content>
                </v-list-item>

                <v-divider class="my-2"></v-divider>

                <v-list-item
                    v-for="(company2,idx) in companies2"
                    :key="`o-${idx}`"
                    link
                    @click="linkTo2(company2.company)"
                    color="grey lighten-4"
                >
                  <v-list-item-content>
                    <v-list-item-title>
                      {{company2.company}}
                    </v-list-item-title>
                  </v-list-item-content>
                </v-list-item>
              </v-list>
            </v-sheet>
          </v-col>

          <v-col >
            <v-sheet
                min-height="70vh"
                align="center"
                rounded="lg"
                color=""
            >

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


            </v-sheet>
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
  data: () => ({
    nationals: [
      {national:'국산'},
      {national:'수입'}
    ],

    individuals1: [
      {individual:'마이페이지', link:"MyPage"}
    ],

    individuals2: [
      {individual:'로그아웃', link:"Car"}
    ],

    companies: [
      {company:'현대'},
      {company:'제네시스'},
      {company:'기아'},
      {company:'쉐보레'},
      {company:'르노코리아'},
      {company:'쌍용'}
    ],

    companies2: [
      {company:'벤츠'},
      {company:'BMW'},
      {company:'아우디'},
    ],

    pickArea: ['서울','부산','대구','인천','경기','대전','광주','울산','충남','충북','강원','경남','경북','전남','전북','제주'],
    area:null,

    users: [],
    cusers: [],
    search: '',
    carName: '',

    link1: "CarWrite",
    link2: "Login",
    link3: "WishList",
    link4: "MyPage",
    link5: "CompanyOfCar",

    page: 1,
    size: 5,
    length: 5,
    totalPage: 0,

    category:'',
    keywords:['작성자', '차량명','지역'],


    loginBtnShow: true,
    wishBtnShow: false,

    //위시리스트
    showSelect: false,
    absolute: true,
    selectWishList: [],
    select: true,

    headers: [
      {text: '', value: 'thumb'},
      {text: '차량정보', value: 'content'},
      {text: '등록일', value: 'writeDate', align: 'center', sortable: false},
      {text: '가격', value: 'price', align: 'center', sortable: false},
    ],


  }),

  methods: {

    retrieveUsers() {
      let data = {};
      data.page = this.page - 1;
      data.size = this.size;
      this.$axios.post("car/page", JSON.stringify(data), {
        headers: {
          "Content-Type": `application/json`,
        },
      }).then(response => {
        console.log(response.data)
        this.users = response.data.content;
        this.totalPage = response.data.totalPages;
        for (let i = 0; i < response.data.length; i++) {
          this.users[i].select = false;
        }
        console.log(response.data);
        if (this.$store.state.userStore.mid == 0) {
          this.loginBtnShow = true
          this.wishBtnShow = false
        } else {
          this.loginBtnShow = false
          this.wishBtnShow = true
        }

      })
          .catch(e => {
            console.log(e);
          })
    },

    keywordSearch() {
      this.$axios.get("car/search", {
        params: {
          category : this.category,
          keyword : this.carName,
          page : this.page-1,
          size : this.size
        }
      })
          .then(response => {
            this.totalPage = response.data.totalPages;
            this.users = response.data.content;
            console.log(response.data.content);
          })
          .catch(e => {
            console.log(e);
          })
    },



    linkTo(data) {
      if(this.$store.state.userStore.mid != 0)
      {
      this.$router.push({name: data})
      }
      else {
        alert("로그인 후 이용가능합니다.")
      }
    },

    linkTo2(company){
      this.$router.push({name:"CompanyOfCar", query: {company : company, page : this.page -1, size: this.size }})
    },

    linkTo3(company){
      if(this.area === null) {
        this.linkTo2(company)
      }

      else {
        this.$router.push({name:"pageCompanyOfArea", query: {company : company, area: this.area, page : this.page -1, size: this.size }})
        this.$router.go(0);
      }
    },

    linkTo4(national){
      this.$router.push({name:"CarOfNational", query: {national: national, page: this.page -1, size: this.size}})
      this.$router.go(0);
    },

    linkTo5(){
      this.$router.push({name:"Car"})
      this.$router.go(0);
    },

    linkTo6() {
      this.$router.push({name:"Login"})
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

    logout() {
      console.log('logout')
      this.$store.dispatch('logout')
      alert("로그아웃 처리 되었습니다.");
    },


    //찜목록 추가
    postWishList() {

    },

    showWishSelectSet(item) {
      console.log(item)
      for (let i = 0; i < 5; i++) {
        if (this.users[i].carId === item) {
          console.log("true")
          this.users[i].select = !this.users[i].select
        }
      }
      console.log(this.users)
    },

    getCompanyOfListPage(company) {
      let data = {};
      data.company = company;
      data.page = this.page - 1;
      data.size = this.size;

      this.$axios.post("car/pageCompany", JSON.stringify(data), {
        headers: {
          "Content-Type": `application/json`,
        },
      })
          .then(response => {
            this.totalPage = response.data.totalPages;
            this.users = response.data.content;
            console.log(response.data.content);
          })

    },

  },

  mounted() {
    this.retrieveUsers();
  },
}
</script>





