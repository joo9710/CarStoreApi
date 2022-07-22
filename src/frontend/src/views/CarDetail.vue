<template>
  <div>
    <v-card color="red" width="600px">
    </v-card>
    <v-card width="600px" height="1500px" >
      <div>작성자 : {{users.author}}</div>
      <div>차량명 : {{users.carName}}</div>
      <div>연식 : {{users.year}}</div>
      <div>주행거리 : {{users.distance}}</div>
      <div>연료 : {{users.fuel}}</div>
      <div>지역 : {{users.area}}</div>
      <div>가격 : {{users.price}}</div>
      <div>내용 : {{users.content}}</div>
      <div>글쓴 날짜 : {{users.writeDate}}</div>
      <div>글쓴 시간 : {{users.writeTime}}</div>
      <div>조회수 : {{users.readCount}}</div>

      <v-img
        v-if="userFile.fileName"
        :src="require('@/assets/'+ userFile.fileName)"
        style="object-fit: cover"
        alt=""/>

      <v-btn @click="putAuthority()">글 수정</v-btn>

      <v-data-table
          :headers="headers"
          :items="cusers"
          class="elevation-1">

      </v-data-table>
    </v-card>
  </div>
</template>
<script>
export default {
  name: 'CarDetail.vue',

  data () {
    return {
      users: '',
      userFile: '',
      cusers:[],
      link1: "Car",
      mid:this.$store.state.userStore.mid,

      headers: [
        {text: '작성자', value: 'author'},
        {text: '내용', value: 'content'},
        {text: '날짜', value: 'writeDate'},
        {text: '시간', value: 'writeDate'},
        { text: 'Actions', value: 'actions',  align: 'center',sortable: false },

      ],
    }
  },

      mounted() {
        this.retrieveUsers();
        this.retrieveUsers2();
        this.showComment();
      },

      methods: {

        linkTo(data){
          this.$router.push({name:data})
        },

        linkTo2(){
          let carId = this.$route.query.carId
          this.$router.push({name:"CarChange", query: { carId: carId }})
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

        retrieveUsers2() {
          let carId = this.$route.query.carId
          this.$axios.get("car/showFile/" + carId)
          .then(response => {
            this.userFile = response.data.data;
            console.log(response.data.data);
          })
          .catch(e => {
            console.log(e);
          })
        },

        showComment() {
          let carId = this.$route.query.carId
          this.$axios.get("comment/" + carId)
          .then(response => {
            this.cusers = response.data.data;
            console.log(response.data.data);
          })
          .catch(e => {
            console.log(e);
          })
        },

      putAuthority() {
          if(this.users.mid == this.mid){
            this.linkTo2(this.link1);
          } else {
            alert("글을 수정할 권한이 없습니다.");
          }
      }

      }
}

</script>

