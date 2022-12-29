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

          <v-col cols="12"
                 md="1">
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
  <v-row justify="center">
    <v-col
      cols="10"
      sm="8"
      md="6"
      lg="4">
      <v-card ref="form">
        <v-card-title>
          차량 등록
        </v-card-title>
        <v-card-text>
          <v-text-field
            ref="nickName"
            v-model="nickName"
            label="작성자"
            placeholder="홍길동"
            required
            readonly
            :color="'rgb(200,80,80)'"
          >
          </v-text-field>

          <v-text-field
            ref="carName"
            v-model="carName"
            label="차량명"
            placeholder="예) 기아 K5 2세대 1.7 디젤 SX 노블레스"
            required
            >
          </v-text-field>

          <v-row class="ma-0">
          <v-col
            cols="2">

          <tr>
            <th>생산지</th>
          </tr>
          </v-col>
          <v-radio-group v-model="national">
            <v-radio value="국산">
              <template v-slot:label>
                <div><strong class="primary--text">국산차</strong></div>
              </template>
            </v-radio>
            <v-radio value="수입">
              <template v-slot:label>
                <div><strong class="primary--text">수입차</strong></div>
              </template>
            </v-radio>
          </v-radio-group>
          </v-row>

          <v-row class="ma-0">
            <v-col
                cols="3">
          <tr>
            <th>차량 명칭</th>
          </tr>
            </v-col>

            <v-combobox
                v-model="subCarName"
                :items="items"
                :search-input.sync="search"
                hide-selected
                hint="예) K5, 그랜저, 제네시스, BMW / 수입차는 회사 입력"
                label="직접입력"
                multiple
                persistent-hint
                small-chips
            >
              <template v-slot:no-data>
                <v-list-item>
                  <v-list-item-content>
                    <v-list-item-title>
                      No results matching "<strong>{{ search }}</strong>". Press <kbd>enter</kbd> to create a new one
                    </v-list-item-title>
                  </v-list-item-content>
                </v-list-item>
              </template>
            </v-combobox>
          </v-row>

          <v-text-field
              ref="year"
              v-model="year"
              label="년식"
              placeholder="예) 20년식"
              required
          >
          </v-text-field>

          <v-text-field
              ref="distance"
              v-model="distance"
              label="주행거리"
              placeholder="예) 22,013km"
              required
          >
          </v-text-field>

          <v-select
              :items="fuels"
              v-model="fuel"
              :menu-props="{ bottom: true, offsetY: true }"
              label="연료"
          ></v-select>

          <v-text-field
              ref="area"
              v-model="area"
              label="지역"
              placeholder="서울"
              required
              readonly
              :color="'rgb(200,80,80)'"
          >
          </v-text-field>

          <v-text-field
              ref="price"
              v-model="price"
              label="가격"
              placeholder="예) 2,200만원"
              required
          >
          </v-text-field>

          <v-textarea v-model="content" ref="content"
              filled
              내용을 입력하세요
              name="input-7-4"
              label="차량정보 입력"
          ></v-textarea>




          <tr style="height: 160px">
            <v-row>
            <v-col cols="12" md="6">
              <v-card style="width: 200px; height: 200px">
                <img
                    :src="thumbUrl"
                    style="object-fit: cover; width: 200px; height: 200px;"
                    alt=""
                >
              </v-card>

              <div class="file-div">
                <h5>썸네일 이미지 선택</h5>
                <input @change="upload2" type="file" id="thumb" accept="image/png, image/jpeg">
                <label class="input-label" for="file"><v-icon color="white">mdi-pencil</v-icon></label>
              </div>

            </v-col>

            <v-col cols="12" md="6">
              <v-card style="width: 200px; height: 200px">
                <img
                    :src="imageUrl"
                    style="object-fit: cover; width: 200px; height: 200px;"
                    alt=""
                >
              </v-card>

              <div class="file-div">
                <h5>게시글 이미지 선택</h5>
                <input @change="upload" ref="imageFile" type="file" id="file"
                       accept="image/png, image/jpeg" multiple>
                <label class="input-label" for="file"><v-icon color="white">mdi-pencil</v-icon></label>
              </div>

            </v-col>
            </v-row>
          </tr>
        </v-card-text>

        <v-divider class="mt-12"></v-divider>
        <v-card-actions>
          <v-btn
              color="primary"
              text
              @click="linkTo6()"
          >
            취소
          </v-btn>

          <v-spacer></v-spacer>

          <v-btn
              color="primary"
              text
              @click="editImage"
          >
            완료
          </v-btn>

        </v-card-actions>
      </v-card>

    </v-col>
  </v-row>
    </v-main>
  </v-app>
</template>

<script>
import axios from "axios";

export default {
  name: "CarWrite.vue",

  data() {
    return {
      nationals: [
        {national:'국산'},
        {national:'수입'}
      ],

      individuals1: [
        {individual:'마이페이지', link:"MyPage"}
      ],



      category:'',
      keywords:['작성자', '차량명','지역'],

      mid: this.$store.state.userStore.mid,
      nickName: this.$store.state.userStore.nickName,
      carName:'',
      year:'',
      distance:'',
      area:this.$store.state.userStore.area,
      price:'',
      content:'',
      national:'',
      subCarName:'',

      items:['K5','모닝','카니발','레이', '쏘렌토', '스포티지', '제네시스', '그랜저', '쏘나타','그랜저', '아반떼','싼타페', '투싼', '스타렉스','스파크','말리부','스파크','크루즈','트랙스','올란도','마티즈','SM5','SM6','QM6','QM3','SM3','SM7','렉스턴','코란도','티볼리','벤츠', 'BMW', '아우디'],

      link1: "Car",

      imageUrl: this.$store.state.userStore.fileName,
      thumbUrl:this.$store.state.userStore.thumb,
      thumbFile:[],
      imgFile:{},

      fuels: ['가솔린', '디젤', 'LPG', '전기', '수소', '하이브리드'],
      fuel:'',

      dialog:false,
      dialogMsg:'',

      radios: '',
      search: null,

      loginBtnShow: false,
      wishBtnShow: false,

    }

  },

  watch: {
    subCarName (val) {
      if (val.length > 1) {
        this.$nextTick(() => this.subCarName.pop())
      }
    }
  },

  methods: {

    editImage() {
      console.log(this.thumbFile)
      console.log(this.imgFile)
      let data = new FormData();
      for(let i =0; i<this.imgFile.length; i++){
      data.append("file", this.imgFile[i]);
      }
      data.append("thumb", this.thumbFile);
      data.append("mid", this.mid);
      data.append("author", JSON.stringify(this.nickName));
      data.append("carName", JSON.stringify(this.carName));
      data.append("national", JSON.stringify(this.national));
      data.append("subCarName", JSON.stringify(this.subCarName));
      data.append("year", JSON.stringify(this.year));
      data.append("distance", JSON.stringify(this.distance));
      data.append("fuel", JSON.stringify(this.fuel));
      data.append("area", JSON.stringify(this.area));
      data.append("price", JSON.stringify(this.price));
      data.append("content", JSON.stringify(this.content));


      axios({
        method: 'post',
        url: '/car/img',
        data: data,
        headers: {
          "Content-Type": "multipart/form-data", // Content-Type 주의
        },
      }).then(response => {
        if(response.status === 200) {
        console.log(response.data)
          alert("글이 작성되었습니다.");
          this.linkTo(this.link1);
          console.log(this.thumbFile);
          console.log(this.imgFile);
        }
      }).catch(error => {
        console.log(error.response);
      })
    },

    upload() {
      // let imageFile = e.target.files;
      this.imgFile = this.$refs.imageFile.files;
      this.imageUrl = URL.createObjectURL(this.$refs.imageFile.files[0]);
      console.log("imgFile: " + this.imgFile);
    },

    upload2(e) {
      let thumbFile = e.target.files;
      this.thumbFile = thumbFile[0]
      this.thumbUrl = URL.createObjectURL(thumbFile[0]);
    },

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

    retrieveUsers() {
      if (this.$store.state.userStore.mid == 0) {
        this.loginBtnShow = true
        this.wishBtnShow = false
      } else {
        this.loginBtnShow = false
        this.wishBtnShow = true
      }
    }
  },

  mounted() {
    this.retrieveUsers();
  }

}

</script>

