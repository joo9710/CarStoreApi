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
              <v-icon>mdi-magnify</v-icon>
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
                  @change="linkTo3(area)"
                  >
                  </v-select>
                </v-list-item>

                <v-list-group
                  :value="true"
                  v-for="item in items"
                  :key="item.title"
                  v-model="item.active"

                  no-action
                >
                <template v-slot:activator>
                  <v-list-item-content>
                    <v-list-item-title
                      v-text="item.title"
                      @click="linkTo2(item.title)"
                    >
                    </v-list-item-title>
                  </v-list-item-content>
                </template>

                  <v-list-item
                    :value="true"
                    v-for="detail in item.items"
                    :key="detail.title">

                    <v-list-item-content>
                      <v-list-item-title
                          @click="linkTo2(detail.title)"
                          class="blue--text"
                          v-text="detail.title"></v-list-item-title>
                    </v-list-item-content>
                  </v-list-item>

                </v-list-group>



                <v-divider>
                </v-divider>




                <v-list-group
                    :value="true"
                    v-for="item in items2"
                    :key="item.title"
                    v-model="item.active"

                    no-action
                >
                  <template v-slot:activator>
                    <v-list-item-content>
                      <v-list-item-title
                          v-text="item.title"
                          @click="linkTo2(item.title)"
                      >
                      </v-list-item-title>
                    </v-list-item-content>
                  </template>

                  <v-list-item
                      :value="true"
                      v-for="detail in item.items"
                      :key="detail.title">

                    <v-list-item-content>
                      <v-list-item-title
                          @click="linkTo2(detail.title)"
                          class="blue--text"
                          v-text="detail.title"></v-list-item-title>
                    </v-list-item-content>
                  </v-list-item>

                </v-list-group>

              </v-list>
            </v-sheet>
          </v-col>

          <v-col>
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
                        @input="getCompanyOfListPage"
                    ></v-pagination>
                  </div>
                </template>

              </div>

              <!--  -->
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
  name: 'CompanyOfCar.vue',

  data() {
    return {
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


      users: [],
      cusers: [],
      search: '',
      carName: '',

      link1: "CarWrite",
      link2: "Login",
      link3: "WishList",
      link4: "MyPage",

      length: 5,
      totalPage: 0,

      company :this.$route.query.company,
      page: 1,
      size: 5,

      pickArea: ['서울','부산','대구'],
      area:'',


      category:'',
      keywords:['작성자', '차량명','지역'],


      loginBtnShow: false,
      wishBtnShow: false,

      headers: [
        {text: '', value: 'thumb'},
        {text: '차량정보', value: 'content'},
        {text: '등록일', value: 'writeDate', align: 'center', sortable: false},
        {text: '가격', value: 'price', align: 'center', sortable: false},
      ],

      items: [
        {
          active: '',
          items: [
            { title: '그랜저' },
            { title: '쏘나타' },
            { title: '아반떼' },
            { title: '싼타페' },
            { title: '투싼' },
            { title: '스타렉스' },

          ],
          title: '현대',
        },

        {
          active: '',
          items: [
            { title: 'EQ900' },
            { title: 'G70' },
            { title: 'G80' },
            { title: 'G90' },
            { title: 'GV80' },
            { title: '쿠페'},
            { title: 'DH' },
          ],
          title: '제네시스',
        },

        {
          active: '',
          items: [
            { title: 'K5' },
            { title: '모닝' },
            { title: '카니발' },
            { title: '레이' },
            { title: '쏘렌토' },
            { title: '스포티지' },
            {title: '스팅어'}
          ],
          title: '기아',
        },

        {
          active: '',
          items: [
            { title: '스파크' },
            { title: '말리부' },
            { title: '크루즈' },
          ],
          title: '쉐보레',
        },

       {
         active: '',
         items: [
           { title: 'SM5' },
           { title: 'SM6' },
           { title: 'QM6' },
         ],
          title: '르노코리아',
        },

        {

          items: [
            { title: '티볼리' },
            { title: '코란도' },
          ],
          title: '쌍용',
        },

      ],

      items2: [
        {
          active: '',
          items: [
            { title: 'E-클래스' },
            { title: 'S-클래스' },
            { title: 'C-클래스' },
            { title: 'CLA-클래스' },
            { title: 'CLS-클래스' },
            { title: 'GLE-클래스' },

          ],
          title: '벤츠',
        },

        {
          active: '',
          items: [
            { title: '3시리즈' },
            { title: '5시리즈' },
            { title: '7시리즈' },
            { title: 'X1' },
            { title: 'X2' },
            { title: 'X3' },
            { title: 'X4' },
            { title: 'X5' },
            { title: 'X6' },
            { title: 'X7' },


          ],
          title: 'BMW',
        },

        {
          active: '',
          items: [
            { title: 'A3' },
            { title: 'A4' },
            { title: 'A5' },
            { title: 'A6' },
            { title: 'A7' },
            { title: 'A8' },
            { title: 'Q5' },
            { title: 'Q7' },

          ],
          title: '아우디',
        },

      ],

      showForeign:false,
      showKorean:false

    }
  },

  methods: {
    linkTo(data) {
      this.$router.push({name: data})
    },

    linkTo2(company){
      this.$router.push({name:"CompanyOfCar", query: {company : company, page : this.page -1, size: this.size }})
      this.$router.go(0);
      this.items.active=true;
    },

    linkTo3(area){
      this.$router.push({name:"pageCompanyOfArea", query: {company : this.company, area: area, page : this.page -1, size: this.size }})
      this.$router.go(0);
    },

    linkTo4(national){
      this.$router.push({name:"CarOfNational", query: {national: national, page: this.page -1, size: this.size}})
    },

    linkTo5(){
      this.$router.push({name:"Car"})
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

    getCompanyOfListPage() {

      this.$axios.get("car/pageCompany", {
        params: {
          company: this.company,
          page : this.page -1,
          size : this.size
        }
      })
          .then(response => {
            this.totalPage = response.data.totalPages;
            this.users = response.data.content;
            if(this.items.includes(this.company)) {
              this.showKorean = true;
            } else
              this.showKorean = false;
            console.log(response.data.content);

          })
          .catch(e => {
            console.log(e);
          })

    },


    getConsole() {
      console.log("company : " + this.company)

      if (this.$store.state.userStore.mid == 0) {
        this.loginBtnShow = true
        this.wishBtnShow = false
      } else {
        this.loginBtnShow = false
        this.wishBtnShow = true
      }
    },



  },

  mounted() {
    this.getCompanyOfListPage();
    this.getConsole();
  },
}
</script>