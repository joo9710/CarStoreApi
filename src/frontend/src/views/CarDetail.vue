<template>
  <div>
    <v-card width="650px" height="790px" >
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
      <v-btn @click="delAuthority()">글 삭제</v-btn>
      <v-btn @click="linkTo(link1)">글 목록</v-btn>

    </v-card>

    <v-card width="650px" height="1500px" >
      <v-form>
        <v-container>
          <v-row>
            <v-col cols="12">
              <v-text-field
                  v-model="content"
                  :append-outer-icon="'mdi-send'"
                  filled
                  clear-icon="mdi-close-circle"
                  clearable
                  label="댓글을 입력하세요"
                  type="text"
                  @click:append-outer="writeComment"
                  @click:clear="clearComment"
              ></v-text-field>
            </v-col>
          </v-row>
        </v-container>
      </v-form>

      <v-data-table
          :headers="headers"
          :items="cusers"
          class="elevation-1">

        <template v-slot:item.actions="{ item }">

          <v-icon
              small
              class="mr-2"
              @click="openDialog(item.cid,item.mid)"
          >
            mdi-pencil
          </v-icon>
            /
          <v-icon
              class="mr-2"
              @click="linkTo5(item.cid)"
          >
            mdi-delete
          </v-icon>
        </template>
      </v-data-table>


    </v-card>


  <CarCommentChange
    ref="CarComment"
  >
  </CarCommentChange>

  </div>
</template>
<script>


import CarCommentChange from "@/views/CarCommentChange";
export default {
  name: 'CarDetail.vue',
  components: {CarCommentChange},
  data () {
    return {
      users: '',
      userFile: '',
      cusers:[],
      cuser:'',
      link1: "Car",
      mid:this.$store.state.userStore.mid,
      nickName:this.$store.state.userStore.nickName,
      content: '',

      headers: [
        {text: '작성자', value: 'author'},
        {text: '내용', value: 'content'},
        {text: '날짜', value: 'writeDate'},
        {text: '시간', value: 'writeTime'},
        { text: '수정  /  삭제', value: 'actions',  align: 'center',sortable: false },

      ],


    }
  },

      mounted() {
        this.retrieveUsers();
        this.retrieveUsers2();
        this.showComment();
      },

      methods: {

        openDialog(cid,mid){
          console.log("dialog : " + cid)
          if(mid==this.mid){
          this.$refs.CarComment.childDialog(cid)
          }
          else{
            alert("댓글 수정 권한이 없습니다.");
          }
        },

        linkTo(data){
          this.$router.push({name:data})
        },

        linkTo2(){
          let carId = this.$route.query.carId
          this.$router.push({name:"CarChange", query: { carId: carId }})
        },

        //글 상세내용 보여주기
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

        //이미지 보여주기(fileNmae읽기)
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

        //댓글 쓰기
        writeComment () {
          let data = {}
          data.carId = this.$route.query.carId
          data.mid = this.mid
          data.author = this.nickName
          data.content = this.content

          this.$axios.post("comment/", JSON.stringify(data), {
            headers: {
              "Content-Type": `application/json`,
            },
          }).then(res => {
            if(res.data.success===true) {
              alert('댓글이 등록되었습니다.')
              this.$router.go(0);
            } else {
              alert("실행중 실패했습니다.");
            }
          })
            .catch((err)=>{
              console.log(err);
            })
        },

        clearComment () {
          this.message = ''
        },

        //댓글보여주기
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

        //해당하는 cid 댓글 불러오기

        //수정권한 mid비교
      putAuthority() {
          if(this.users.mid == this.mid){
            this.linkTo2(this.link1);
          } else {
            alert("글을 수정할 권한이 없습니다.");
          }
      },

        //삭제 권한 확인 및 삭제
        delAuthority() {
          if(this.users.mid == this.mid){
            let carId = this.$route.query.carId
            this.$axios.delete("car/" + carId)
              alert("삭제 되었습니다.");
            this.$router.go(-1);
          }else alert("글 삭제 권한이 없습니다.");
        }

      }
}

</script>

