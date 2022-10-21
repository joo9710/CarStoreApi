<template>
  <v-row justify="center">
    <v-col
      cols="10"
      sm="8"
      md="6"
      lg="4">
      <v-card ref="form">
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
                  <input @change="upload2" type="file" id="thumb" accept="image/png, image/jpeg" multiple>
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
                  <input @change="upload" type="file" id="file" accept="image/png, image/jpeg" multiple>
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

  <div>
    <h1>차량정보 수정</h1>

    <div class="AddWrap">
      <form>
        <table class="tbAdd">
          <colgroup>
            <col width="15%" />
            <col width="*" />
          </colgroup>

          <tr>
            <th>차량명</th>
            <td><input type="carName" v-model="users.carName" ref="carName" /></td>
          </tr>

          <tr>
            <th>주행거리</th>
            <td><textarea v-model="users.distance" ref="distance"></textarea></td>
          </tr>

          <tr>
            <th>지역</th>
            <td><textarea v-model="users.area" ref="area"></textarea></td>
          </tr>

          <tr>
            <th>가격</th>
            <td><textarea v-model="users.price" ref="users.price"></textarea></td>
          </tr>

          <tr>
            <th>내용</th>
            <td><textarea v-model="users.content" ref="content"></textarea></td>
          </tr>

        </table>
      </form>

      <tr style="height: 160px">
        <v-col>
          <v-card style="width: 200px; height: 200px">
            <img v-for="(item,i) in imageUrl" :key="i" :src="item.url"
                style="object-fit: cover; width: 200px; height: 200px;"
                alt=""
            >
          </v-card>

          <div class="file-div">
            <h5>게시글 이미지 선택</h5>
            <input type="file" id="file" @change="upload" accept="image/png, image/jpeg" multiple>
            <label class="input-label" for="file"><v-icon color="white">mdi-pencil</v-icon></label>
          </div>

        </v-col>
      </tr>

      <tr style="height: 160px">
        <v-col>
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
      </tr>



      <div class="btnWrap">
        <v-btn @click="change">완료</v-btn>
        <v-btn @click="linkTo(link1)" >취소</v-btn>
      </div>

    </div>


  </div>

    </v-col>
  </v-row>
</template>

<script>
export default {
  name: "CarChange.vue",

  data() {
    return {
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

