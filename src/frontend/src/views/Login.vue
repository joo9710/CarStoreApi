<template>
  <v-app id="app">
    <v-main>
      <v-container
          style="position: relative; top: 20%; margin-left: 20%"
          class="text-xs-center">
        <v-layout row wrap class="text-xs-center">
          <v-flex>
            <v-card flat class="mx-auto" max-width="800">
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
      link1:"SignUp"
    };
  },
  methods: {
    linkTo(data){
      this.$router.push({name: data})
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
