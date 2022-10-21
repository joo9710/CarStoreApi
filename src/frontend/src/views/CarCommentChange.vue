<template>
  <v-dialog
      v-model="dialog"
      width="500"
  >
    <v-card>
      <v-card-title class="text-h5 grey lighten-2">
        댓글수정
      </v-card-title>

      <v-text-field
          v-model="cuser.content"
          placeholder="댓글을 입력하세요"
      />
      <v-divider></v-divider>
      <v-card-actions>

        <v-spacer></v-spacer>
        <v-btn
            color="primary"
            text
            @click="change"
        >
          댓글 수정
        </v-btn>
        <v-btn
            color="primary"
            text
            @click="dialog = false"
        >
          취소
        </v-btn>
      </v-card-actions>
    </v-card>
  </v-dialog>
</template>

<script>
export default {
  name: "CarCommentChange.vue",

  data() {
    return {
      cuser:[],
      dialog:false,
      cid: 0,
      content:'',
    }
  },

  methods: {
    putChildDialog(cid){
      console.log('child cid '+ cid);
      this.cid =cid;
      this.dialog = true;

    },

    change(){
      console.log(this.content)
      let data = {};
      data.content = this.cuser.content

      this.$axios.put("comment/" + this.cid, JSON.stringify(data), {
        headers: {
          "Content-Type": `application/json`,
          Authorization: "Bearer " + this.$store.state.userStore.token
        },
      }).then((res)=>{
        if(res.data){
          alert('수정되었습니다.');
          this.$router.go(0);
        } else {
          alert("실행중 실패했습니다.");
        }
      })
          .catch((err)=>{
            console.log(err);
          })
    },
  },
}
</script>

