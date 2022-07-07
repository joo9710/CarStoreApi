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
    </div>

    <div class="btnWrap">
      <v-btn @click="write">글 쓰기</v-btn>
      <v-btn @click="linkTo(link1)">취소</v-btn>
    </div>
  </div>
</template>

<script>
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
      link1: "Car"
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

    linkTo(data) {
      this.$router.push({name: data})
    }
  }

}
</script>

