<template>
  <div>
    <h1>차량 등록</h1>

    <div class="AddWrap">
      <form>
        <table class="tbAdd">
          <colgroup>
            <col width="15%" />
            <col width="*" />
          </colgroup>
          <tr>
            <th>작성자</th>
            <td><input type="text" v-model="nickName" ref="nickName" /></td>
          </tr>
          <tr>
            <th>차량명</th>
            <td><input type="carName" v-model="carName" ref="carName" /></td>
          </tr>
          <tr>
            <th>연식</th>
            <td><input type="text" v-model="year" ref="year" /></td>
          </tr>
          <tr>
            <th>주행거리</th>
            <td><textarea v-model="distance" ref="distance"></textarea></td>
          </tr>
          <tr>
            <th>연료</th>
            <td><textarea v-model="fuel" ref="fuel"></textarea></td>
          </tr>
          <tr>
            <th>지역</th>
            <td><textarea v-model="area" ref="area"></textarea></td>
          </tr>
          <tr>
            <th>가격</th>
            <td><textarea v-model="price" ref="price"></textarea></td>
          </tr>
          <tr>
            <th>내용</th>
            <td><textarea v-model="content" ref="content"></textarea></td>
          </tr>
        </table>
      </form>

      <tr style="height: 160px">
          <v-col>
            <v-card style="width: 200px; height: 200px">
              <img
                  :src="imageUrl"
                  style="object-fit: cover; width: 200px; height: 200px;"
                  alt=""
              >
            </v-card>

            <div class="file-div">
              <h5>게시글 이미지 선택</h5>
              <input @change="upload" type="file" id="file" accept="image/png, image/jpeg">
              <label class="input-label" for="file"><v-icon color="white">mdi-pencil</v-icon></label>
            </div>

          </v-col>
      </tr>
    </div>

    <div class="btnWrap">
      <v-btn @click="editImage">글 쓰기</v-btn>
      <v-btn @click="linkTo(link1)">취소</v-btn>
    </div>
  </div>
</template>

<script>
import axios from "axios";

export default {
  name: "CarWrite.vue",

  data() {
    return {
      nickName: this.$store.state.userStore.nickName,
      carName:'',
      year:'',
      distance:'',
      fuel:'',
      area:this.$store.state.userStore.area,
      price:'',
      content:'',

      link1: "Car",

      imageUrl: this.$store.state.userStore.fileName,
      imgFile:[],

      dialog:false,
      dialogMsg:'',
    }
  },

  methods: {
    write() {
      let data = {}
      data.author = this.nickName
      data.carName = this. carName
      data.year = this.year
      data.distance = this.distance
      data.fuel = this.fuel
      data.area = this.area
      data.price = this.price
      data.content = this.content

      this.$axios.post("car/", JSON.stringify(data), {
        headers: {
          "Content-Type": `application/json`,
        },
      }).then((res)=>{
        console.log(res.data)
        if(res.data.success===true) {
          alert('등록되었습니다.');
          this.linkTo(this.link1)
        } else {
          alert("등록이 실패하였습니다");
        }
      })
          .catch((err)=>{
            console.log(err);
          })
    },

    editImage() {
      let data = new FormData();
      data.append("file", this.imgFile);
      data.append("author", JSON.stringify(this.author));
      data.append("carName", JSON.stringify(this.carName));
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
        console.log(response.data)
      }).catch(error => {
        console.log(error.response);
      })
    },

    upload(e) {
      let imageFile = e.target.files;
      this.imgFile = imageFile[0]
      this.imageUrl = URL.createObjectURL(imageFile[0]);
    },

    linkTo(data) {
      this.$router.push({name: data})
    }
  }

}
</script>

