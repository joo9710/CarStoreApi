<template>

  <v-row justify="center">
    <v-col
        cols="10"
        sm="8"
        md="6"
        lg="4"
    >
      <v-card ref="form">

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

    </v-col>
  </v-row>


</template>

<script>


import PutMemberPwd from "@/views/putMemberPwd"

export default {
  name: "putMember.vue",
  components: {PutMemberPwd},

  data() {
    return {
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

