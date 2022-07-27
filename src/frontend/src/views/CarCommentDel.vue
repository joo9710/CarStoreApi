<template>
  <v-dialog
      v-model="dialog"
      width="300"
  >
    <v-card>
      <v-card-title class="text-h5 grey lighten-2">
        댓글삭제
      </v-card-title>

      <span> 댓글을 삭제하시겠습니까?</span>
      <v-divider></v-divider>
      <v-card-actions>

        <v-spacer></v-spacer>
        <v-btn
            color="primary"
            text
            @click="del"
        >
          확인
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
  name: "CarCommentDel.vue",

  data() {
    return {
      dialog:false,
      cid: 0,
    }
  },

  methods: {
    delChildDialog(cid){
      console.log('child cid '+ cid);
      this.cid =cid;
      this.dialog = true;

    },
    del(){
      this.$axios.delete("comment/" + this.cid)
        alert("댓글이 삭제 되었습니다.");
      this.$router.go(0);
    }

  },
}
</script>