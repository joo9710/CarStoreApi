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
              <v-btn icon
                     @click="linkTo7">
                <v-icon

                >mdi-magnify</v-icon>
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
            <v-col cols="12">
  <v-row justify="center">
    <v-col
        cols="8"
        sm="5"
    >
      <v-card ref="form">
        <v-card-title>
          회원가입
        </v-card-title>
        <v-card-text>
          <v-text-field
              ref="fullName"
              v-model="fullName"
              :rules="[() => !!fullName || '이름이 비어있습니다.']"
              label="Full Name"
              placeholder="홍길동"
              required
          ></v-text-field>

          <v-row class="ma-0">
            <v-col cols="9">

          <v-text-field
              ref="email"
              v-model="email"
              :rules="[
              () => !!email || 'email이 비어있습니다.'
              ]"
              label="email"
              placeholder="1234@google.com"
              counter="25"
              required
          ></v-text-field>

            </v-col>
            <v-col cols="3">
              <v-btn
                  color="primary"
                  @click="duplicateCheckEmail()"
              >
                중복확인
              </v-btn>
            </v-col>
          </v-row>
          <v-form>
            <v-text-field
                ref="password"
                v-model="password"
                :append-icon="show1 ? 'mdi-eye' : 'mdi-eye-off'"
                :rules="[() => !!password || 'Password가 비어있습니다.']"
                :type="show1 ? 'text' : 'password'"
                label="Password"
                placeholder="****"
                required
                class="input-group--focused"
                @click:append="show1 = !show1"
                autoComplete="off"
            ></v-text-field>
          </v-form>

          <v-form>
            <v-text-field
                ref="passCheck"
                v-model="passCheck"
                :append-icon="show2 ? 'mdi-eye' : 'mdi-eye-off'"
                :rules="[() => !!passCheck || 'Password가 비어있습니다.', passwordCheck]"
                :error-messages="errorMessages"
                :type="show2 ? 'text' : 'password'"
                label="Password Check"
                placeholder="****"
                required
                class="input-group--focused"
                @click:append="show2 = !show2"
                autoComplete="off"
            ></v-text-field>
          </v-form>

          <v-row class="ma-0">
            <v-col cols="9">

          <v-text-field
              ref="nickName"
              v-model="nickName"
              :rules="[() => !!nickName || '닉네임이 비어있습니다.',
              () => !!nickName && nickName.length <= 10 || '닉네임은 10자 이내로 작성해주세요',]"
              label="닉네임"
              counter="10"
              required
              placeholder="바다"
          ></v-text-field>

            </v-col>

            <v-col cols="3">
              <v-btn
                  color="primary"
                  @click="duplicateCheckNickName()"
              >
                중복확인
              </v-btn>
            </v-col>
          </v-row>

          <v-select
              :items="pickArea"
              ref="area"
              :menu-props="{ bottom: true, offsetY: true }"
              v-model="area"
              :rules="[() => !!area || '지역이 비어있습니다.']"
              label="지역"
              required
              placeholder="서울"
          ></v-select>
          <v-text-field
              ref="phoneNumber"
              v-model="phoneNumber"
              :rules="[() => !!phoneNumber || '연락처가 비어있습니다.']"
              label="연락처"
              required
              placeholder="010-1234-1234"
          ></v-text-field>
        </v-card-text>
        <v-divider class="mt-12"></v-divider>
        <v-card-actions>
          <v-btn
              color="red"
          @click="linkTo(link2)"
          text
          >
            취소
          </v-btn>
          <v-spacer></v-spacer>
          <v-slide-x-reverse-transition>
            <v-tooltip
                v-if="formHasErrors"
                left
            >
            </v-tooltip>
          </v-slide-x-reverse-transition>
          <v-btn
              color="primary"
              text
              @click="submit"
          >
            완료
          </v-btn>
        </v-card-actions>
      </v-card>
    </v-col>
  </v-row>
            </v-col>
          </v-row>
        </v-container>
      </v-main>
    </div>

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
    fullName:'',
    errorMessages: '',
    email: null,
    nickName: null,
    password: null,
    passCheck:null,
    area:null,
    pickArea: ['서울','부산','대구','인천','경기','대전','광주','울산','충남','충북','강원','경남','경북','전남','전북','제주'],

    keywords:['작성자', '차량명','지역'],
    nationals: [
      {national:'국산'},
      {national:'수입'}
    ],

    phoneNumber:null,
    formHasErrors: false,
    show1:false,
    show2:false,
    link1:"Login",
    link2:"Car",

    emailCheck:null,
    nickNameCheck:null

  }),
  watch: {
    name () {
      this.errorMessages = ''
    },
  },
  methods: {
    linkTo(data) {
      this.$router.push({name: data})
    },

    linkTo4(national){
      this.$router.push({name:"CarOfNational", query: {national: national, page: this.page -1, size: this.size}})
      this.$router.go(0);
    },

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

    passwordCheck () {
      this.errorMessages = this.password != this.passCheck
          ? `패스워드가 일치하지 않습니다.`
          : ''
      return true
    },

    submit () {
      if(this.emailCheck == true && this.nickNameCheck == true) {
        if (this.phoneNumber == null) {
          return null;
        } else {
          if (this.password == this.passCheck) {
            let data = {};
            data.fullName = this.fullName;
            data.email = this.email;
            data.password = this.password;
            data.nickName = this.nickName;
            data.area = this.area;
            data.phoneNumber = this.phoneNumber;

            this.$axios.post("/api/member", JSON.stringify(data), {
              headers: {
                "Content-Type": `application/json`,
              },
            }).then((res) => {
              if (res.status === 200) {
                console.log(res.data);
                alert("회원가입이 완료 되었습니다!");
                this.linkTo(this.link1);
              }
            })
                .catch(err => {
                  console.log(err.res);
                })
          } else {
            alert("Password를 다시 확인해 주세요");
          }
        }
      }
      else {
        alert("중복확인을 먼저 해주세요");
      }
    },

    duplicateCheckEmail() {
      let email = this.email;

      this.$axios.get("/member/checkEmail/" + email)
      .then(res => {
        if (res.data === true) {
          alert("이미 사용중인 email 입니다.")
          this.emailCheck = false;
        }
        else {
          alert("사용가능한 email 입니다.")
          this.emailCheck = true;
        }
      })
    },

    duplicateCheckNickName(){
      let nickName = this.nickName;

      this.$axios.get("/member/check/" + nickName)
      .then(res => {
        if(res.data === true) {
          alert("이미 사용중인 닉네임 입니다.")
          this.nickNameCheck = false;
        }
        else {
          alert("사용가능한 닉네임 입니다.")
          this.nickNameCheck = true;
        }
      })
    }
  },
}
</script>