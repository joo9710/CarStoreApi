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
        <v-row>
          <v-col cols="12">


  <v-row justify="center">

      <v-card ref="form" width="650" height="auto">

        <v-card-title>
          회원정보 수정
        </v-card-title>

        <v-card-text>
          <v-form>

            <v-text-field
                ref="fullName"
                v-model="fullName"
                :color="'rgb(200,80,80)'"
                label="Full Name(변경불가)"
                required
                filled
                readonly
            ></v-text-field>

            <v-text-field
                ref="email"
                v-model="email"
                :color="'rgb(200,80,80)'"
                label="email(변경불가)"
                counter="25"
                required
                filled
                readonly
            ></v-text-field>
          </v-form>

          <v-row class="ma-0 ">
            <v-col cols="9">
          <v-text-field
              ref="nickName"
              v-model="nickName"
              :rules="[() => !!nickName || '닉네임이 비어있습니다.',
              () => !!nickName && nickName.length <= 10 || '닉네임은 10자 이내로 작성해주세요',]"
              label="닉네임"
              counter="10"
              outlined
              required
              placeholder="바다">
          </v-text-field>
            </v-col>

            <v-col cols="3">
              <v-btn
                  color="primary"
                  @click="duplicateCheck()"
              >
                중복확인
              </v-btn>
            </v-col>
          </v-row>

          <v-spacer></v-spacer>
          <v-card-actions>

          </v-card-actions>

          <v-text-field
              ref="area"
              v-model="area"
              :color="'rgb(200,80,80)'"
              label="지역(변경불가)"
              required
              filled
              readonly
          ></v-text-field>

          <v-text-field
              ref="phoneNumber"
              v-model="phoneNumber"
              :rules="[() => !!phoneNumber || '연락처가 비어있습니다.']"
              label="연락처"
              outlined
              required
              placeholder="010-1234-1234"
          >
          </v-text-field>


        </v-card-text>
        <v-divider class="mt-12"></v-divider>
        <v-card-actions>
          <v-btn
              color="primary"
              text
              @click="linkTo2()"
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
              color="red"
              text
              @click="openDialogPutMemberPwd(mid)"
          >
            비밀번호 변경
          </v-btn>

          <v-btn
              color="primary"
              text
              @click="putMember()"
          >
            완료
          </v-btn>
        </v-card-actions>

        <putMemberPwd
            ref="putPwd">
        </putMemberPwd>
      </v-card>

  </v-row>
          </v-col>
        </v-row>
      </v-container>
    </v-main>

    <v-divider></v-divider>

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


import PutMemberPwd from "@/views/putMemberPwd"

export default {
  name: "putMember.vue",
  components: {PutMemberPwd},

  data() {
    return {

      nationals: [
        {national:'국산'},
        {national:'수입'}
      ],

      individuals: [
        {individual:'마이페이지', link:"MyPage"}
      ],

      category:'',
      keywords:['작성자', '차량명','지역'],
      carName:'',

      errorMessages: '',

      mid: this.$store.state.userStore.mid,
      fullName: this.$store.state.userStore.fullName,
      nickName: this.$store.state.userStore.nickName,
      email: this.$store.state.userStore.email,
      area: this.$store.state.userStore.area,
      phoneNumber:this.$store.state.userStore.phoneNumber,

      formHasErrors: false,

      show1:false,
      show2:false,

      link1:"Login",
      link2:"Car",

      users:'',

      nickNameCheck:null

    }
},



  mounted() {
    this.getMemberInfo();
  },

  watch: {
    name () {
      this.errorMessages = ''
    },
  },

  methods: {

    linkTo(data) {
      this.$router.push({name: data})
    },

    linkTo2() {
      this.$router.go(-1);
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

    linkTo8() {
      if(this.$store.state.userStore.mid ==0) {
        alert("로그인 후 사용 바랍니다.");
      }else {
        this.$router.push({name:"WishList"});
      }
    },

    getMemberInfo() {
      let mid = this.$store.state.userStore.mid
      this.$axios.get("member/" + mid)
      .then(response => {
        this.users = response.data;
        console.log(response.data);
      })
      .catch(e => {
        console.log(e);
      })
    },

    putMember(){
      if (this.nickNameCheck == true || this.nickNameCheck == null){
      let data = {};
      data.nickName = this.nickName
      data.phoneNumber = this.phoneNumber

      this.$axios.put("member/" + this.mid, JSON.stringify(data),{
        headers: {
          "Content-Type": `application/json`,
          Authorization: "Bearer " + this.$store.state.userStore.token
        },
      }).then((res)=> {
        if(res.data){
            alert('개인정보 수정완료.');
            this.$router.go(-1);
        } else{
          alert('다시 시도해주세요');
        }
      })
      }
      else {
        alert("닉네임을 다시 확인해주세요");
      }
    },

    openDialogPutMemberPwd(mid) {
      this.$refs.putPwd.openChildDialog(mid)
    },

    duplicateCheck() {
      let nickName = this.nickName;

      this.$axios.get("/member/check/" + nickName)
      .then(res => {
        if (res.data === true) {
          alert("이미 사용중인 닉네임 입니다.")
          this.nickNameCheck = false
        }
        else {
          alert("사용가능한 닉네임 입니다.")
          this.nickNameCheck = true
        }
      })
    }

  }

}
</script>

