<template>
  <v-row justify="center">
    <v-col
        cols="10"
        sm="8"
        md="6"
        lg="4"
    >
      <v-card ref="form">
        <v-card-text>
          <v-text-field
              ref="fullName"
              v-model="fullName"
              :rules="[() => !!fullName || '이름이 비어있습니다.']"
              label="Full Name"
              placeholder="홍길동"
              required
          ></v-text-field>
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

          <v-text-field
              ref="area"
              v-model="area"
              :rules="[() => !!area || '지역이 비어있습니다.']"
              label="지역"
              required
              placeholder="서울"
          ></v-text-field>
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
          @click="linkTo(link2)"
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
    phoneNumber:null,
    formHasErrors: false,
    show1:false,
    show2:false,
    link1:"Login",
    link2:"Car"
  }),
  computed: {
    form () {
      return {
        name: this.name,
        address: this.address,
        city: this.city,
        state: this.state,
        zip: this.zip,
        country: this.country,
      }
    },
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

    passwordCheck () {
      this.errorMessages = this.password != this.passCheck
          ? `패스워드가 일치하지 않습니다.`
          : ''
      return true
    },

    submit () {
    if(this.phoneNumber == null){
      return null;
    } else{
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
    },
  },
}
</script>