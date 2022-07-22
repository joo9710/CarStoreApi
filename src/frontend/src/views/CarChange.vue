<template>
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
            <td><input type="carName" v-model="carName" ref="carName" /></td>
          </tr>

          <tr>
            <th>주행거리</th>
            <td><textarea v-model="distance" ref="distance"></textarea></td>
          </tr>

          <tr>
            <th>지역</th>
            <td><textarea v-model="area" ref="area"></textarea></td>
          </tr>

          <tr>
            <th>가격</th>
            <td><textarea v-model="price" ref="users.price"></textarea></td>
          </tr>

          <tr>
            <th>내용</th>
            <td><textarea v-model="content" ref="content"></textarea></td>
          </tr>

        </table>
      </form>

      <div class="btnWrap">
        <v-btn @click="change">완료</v-btn>
        <v-btn @click="linkTo(link1)" >취소</v-btn>
      </div>

    </div>


  </div>
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
      data.carName = this.carName
      data.distance= this.distance
      data.area = this.area
      data.price = this.price
      data.content = this.content

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


    }

  }
}
</script>

