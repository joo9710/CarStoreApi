<template>
  <v-app id="app">
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


        </v-row>


      <v-row
          align="center"
          justify="space-around">
        <v-col cols=12
               md="6"
               class="">

          <v-btn
              tile
              color="primary"
              @click="linkTo3"
          >
            <v-icon left>
              mdi-arrow-left
            </v-icon>
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
        <v-layout row wrap class="text-xs-center">
          <v-flex>
            <v-card flat class="mx-auto" width="400">
              <v-row style="margin-top: 60px">
                <v-col>
                  <v-form style="width: 400px; height: 300px">
                    <div class="mx-3">
                      <v-icon color="black" size="30px"></v-icon>
                      Email
                      <div class="mx-1">
                        <v-text-field
                            placeholder="Email"
                            v-model="userId"
                            required
                        ></v-text-field>
                      </div>
                    </div>
                    <div class="mx-3">
                      <v-icon color="black" size="30px"></v-icon>
                      Password
                      <div class="mx-1">
                        <v-text-field
                            placeholder="userPassword"
                            type="password"
                            v-model="userPassword"
                            required
                        ></v-text-field>
                      </div>
                    </div>

                    <v-card-actions>
                      <v-btn
                          color="#2c4f91"
                          dark
                          large
                          block
                          @click="loginSubmit"
                      >로그인</v-btn
                      >
                    </v-card-actions>

                    <v-card-actions>
                      <v-btn
                          color="#2c4f91"
                          dark
                          large
                          block
                          @click="linkTo(link1)"
                      >회원가입</v-btn
                      >
                    </v-card-actions>
                  </v-form>
                </v-col>
              </v-row>
            </v-card>
          </v-flex>
        </v-layout>
      </v-container>

    </v-main>
  </v-app>
</template>

<script>
export default {
  data() {
    return {
      userId: "123@naver.com",
      userPassword: "123",
      token:'',
      nickName:'',
      link1:"SignUp",

      nationals: [
        {national:'국산'},
        {national:'수입'}
      ],

      keywords:['작성자', '차량명','지역'],

    };
  },
  methods: {
    linkTo(data){
      this.$router.push({name: data})
    },

    linkTo3() {
      this.$router.go(-1);
    },

    linkTo4(national){
      this.$router.push({name:"CarOfNational", query: {national: national, page: this.page -1, size: this.size}})
      this.$router.go(0);
    },

    linkTo5(){
      this.$router.push({name:"Car"})
    },

    linkTo7() {
      this.$router.push({name:"CarSearch", query: {category: this.category, keyword: this.carName, page: this.page-1, size: this.size}})
    },

    loginSubmit() {
      let Data = {};
      Data.email = this.userId;
      Data.password = this.userPassword;
      try {
        this.$axios.post("/authenticate", JSON.stringify(Data), {
          headers: {
            "Content-Type": `application/json`,
          },
        }).then((res) => {
          console.log(res)
          if (res.status === 200) {
            alert("로그인 성공")
            this.$store.dispatch('login', res.data);
            this.$router.push("Car");

            // 로그인 성공시 처리해줘야할 부분
          }
        })
            .catch(error => {
              console.log(error.response);
            })
      }catch (error) {
        console.error(error);
      }
      },
  },
};
</script>
