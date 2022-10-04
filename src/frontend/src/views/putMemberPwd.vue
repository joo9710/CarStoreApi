<template>
  <v-dialog
      v-model="dialog"
      width="500"
  >
    <v-card ref="form">

          <v-card-title>
            비밀번호 수정
          </v-card-title>

          <v-card-text>

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

          </v-card-text>
          <v-divider class="mt-12"></v-divider>
          <v-card-actions>
            <v-btn
                color="primary"
                text
                @click="dialog = false"
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
                @click="putMemberPwd"
            >
              완료
            </v-btn>
          </v-card-actions>
        </v-card>


  </v-dialog>
</template>

<script>
export default {
  name: "putMemberPwd.vue",

  data() {
    return {
      dialog:false,

      mid:'',
      formHasErrors: false,
      show1:false,
      show2:false,
      password: null,
      passCheck:null,
      errorMessages: '',

    }
  },

  watch: {
    name () {
      this.errorMessages = ''
    },
  },

  methods: {
    openChildDialog(mid){
      this.mid=mid;
      this.dialog=true;
    },

    passwordCheck () {
      this.errorMessages = this.password != this.passCheck
          ? `패스워드가 일치하지 않습니다.`
          : ''
      return true
    },

    putMemberPwd() {
      let data = {};
      data.password = this.password

      this.$axios.put("member/pwd/" + this.mid, JSON.stringify(data), {
        headers: {
          "Content-Type": `application/json`,
          Authorization: "Bearer " + this.$store.state.userStore.token
        },
      }).then((res)=> {
        if(res.data){
          alert('비밀번호 수정완료.');
          this.dialog=false;
        } else{
          alert('다시 시도해주세요');
        }
      })
    }
  }
}
</script>

