<template>
  <div>
    <v-row justify="center">
    <v-card width="830px" height="900px">


      <v-text-field
          v-model="search"
          append-icon="mdi-magnify"
          label="Search"
          single-line
          hide-details
      ></v-text-field>

      <v-data-table
          :headers="headers"
          :items="users"
          :search="search"
          :items-per-page="5"
          class="elevation-1">

          <template v-slot:item.action="{item}">
            <v-icon
                small
                class="mr2"
                @click="showWishSelectSet(item.carId)" :color="item.select ? 'rgb(200,80,80)' : 'rgb(180,180,180)'"
                >mdi-heart
            </v-icon>

          </template>

        <template v-slot:top>
          <v-toolbar
              flat
          >

            <div v-show="wishBtnShow">
            <v-btn @click="linkTo(link1)">글 쓰기</v-btn>
            <v-btn @click="linkTo(link3)">나의 찜목록</v-btn>
            </div>

            <v-spacer></v-spacer>

            <div v-show = "loginBtnShow">
            <v-btn @click="linkTo(link2)">로그인</v-btn>
            </div>

            <div v-show="wishBtnShow">
            <v-btn @click="logout">로그아웃</v-btn>
            </div>
          </v-toolbar>



        </template>

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
              <span>{{item.year}}</span>
              <span>{{item.distance}}</span>
              <span>{{item.fuel}}</span>
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

    </v-card>
    </v-row>
  </div>

</template>

<script>
export default {
  name: "Car",

  data: () => ({
      users: [],
    search: '',
    link1:"CarWrite",
    link2:"Login",
    link3:"WishList",
    page:1,
    size:5,
    length:5,
    totalPage:0,

    loginBtnShow:false,
    wishBtnShow:false,

    //위시리스트
    showSelect : false,
    absolute: true,
    selectWishList: [],
    select:true,



    wishCars:[],

      headers: [
        { text: '', value: 'thumb' },
        { text: '차량정보', value: 'content' },
        { text: '찜하기', value: 'action', align:'center', sortable: false},
        { text: '가격', value: 'price',  align: 'center',sortable: false },
      ],

  }),

  watch: {
    select (msg) {
      console.log('new wish' + msg);

    },
  },

  methods: {

    retrieveUsers() {
      let data = {};
      data.page = this.page-1;
      data.size = this.size;
      this.$axios.post("car/page", JSON.stringify(data),{
            headers: {
              "Content-Type": `application/json`,
            },
          }).then(response => {
            console.log(response.data)

            this.users = response.data.content;
            this.totalPage = response.data.totalPages;
            for(let i=0;i<response.data.length;i++){
              this.users[i].select = false;
            }
            console.log(response.data);
            if(this.$store.state.userStore.mid==0) {
              this.loginBtnShow =true
              this.wishBtnShow = false
            }
            else {
              this.loginBtnShow=false
              this.wishBtnShow=true
            }


          })
          .catch(e => {
            console.log(e);
          })
    },


    linkTo(data){
      this.$router.push({name: data})
    },

    logout(){
      console.log('logout')
      this.$store.dispatch('logout')
      alert("로그아웃 처리 되었습니다.");

    },


    //찜목록 추가
    postWishList(){

    },

    showWishSelectSet(item){
      console.log(item)
      for(let i=0; i<5; i++){
        if(this.users[i].carId === item){
          console.log("true")
          this.users[i].select = !this.users[i].select
        }
      }
      console.log(this.users)
    }
  },

  mounted() {
    this.retrieveUsers();
  },

}

</script>

