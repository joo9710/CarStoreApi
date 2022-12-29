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

      <v-card ref="form" width="750" height="auto">
        <v-card-title>
          차량정보 수정
        </v-card-title>
        <v-card-text>

          <v-text-field
              ref="carName"
              v-model="users.carName"
              label="차량명"
              placeholder="예) 기아 K5 2세대 1.7 디젤 SX 노블레스"
              required
          >
          </v-text-field>


          <v-text-field
              ref="distance"
              v-model="users.distance"
              label="주행거리"
              placeholder="예) 22,013km"
              required
          >
          </v-text-field>

          <v-text-field
              ref="price"
              v-model="users.price"
              label="가격"
              placeholder="예) 2,200만원"
              required
          >
          </v-text-field>

          <v-textarea v-model="users.content" ref="content"
                      filled
                      내용을 입력하세요
                      name="input-7-4"
                      label="차량정보 입력"
          ></v-textarea>


        </v-card-text>

        <v-divider class="mt-12"></v-divider>
        <v-card-actions>
          <v-btn
              color="primary"
              text
              @click="linkTo(link1)"
          >
            취소
          </v-btn>

          <v-spacer></v-spacer>

          <v-btn
              color="primary"
              text
              @click="change"
          >
            완료
          </v-btn>

        </v-card-actions>

      </v-card>


  </v-row>
          </v-col>
        </v-row>
      </v-container>
    </v-main>
  </v-app>
</template>

<script>
export default {
  name: "CarChange.vue",

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

      users:'',
      area:this.$store.state.userStore.area,
      carName:'',
      distance:'',
      price:'',
      content:'',
      link1: "Car",

      thumbUrl:this.$store.state.userStore.thumb,
      imageUrl: this.$store.state.userStore.fileName,
      imgFile:[],

      dialog:false,
      dialogMsg:'',
    }
  },

  mounted() {
    this.retrieveUsers();
  },

  methods:{
    linkTo(data) {
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



    retrieveUsers() {
      let carId = this.$route.query.carId
      this.$axios.get("car/" + carId)
          .then(response => {
            this.users = response.data.data;
            console.log(response.data.data);
          })
          .catch(e => {
            console.log(e);
          })
    },

    change() {
      let data = {}
      data.carName = this.users.carName
      data.distance= this.users.distance
      data.area = this.users.area
      data.price = this.users.price
      data.content = this.users.content

      let carId = this.$route.query.carId
      this.$axios.put("car/" + carId, JSON.stringify(data), {
        headers: {
          "Content-Type": `application/json`,
        },
      })
      .then((res)=>{
        if(res.data) {
          alert('수정되었습니다.');
          this.linkTo(this.link1);
        } else {
          alert("실행중 실패했습니다.");
        }
      })
      .catch((err)=>{
        console.log(err);
      })


    },

    upload(e) {
      let imageFile = e.target.files;
      this.imgFile = imageFile[0]
      this.imageUrl = URL.createObjectURL(imageFile[0]);
    },

    upload2(e) {
      let thumbFile = e.target.files;
      this.thumbFile = thumbFile[0]
      this.thumbUrl = URL.createObjectURL(thumbFile[0]);
    },

  }
}
</script>

