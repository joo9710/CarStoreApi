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
              <v-icon>mdi-magnify</v-icon>
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

          <div v-show="wishBtnShow">
            <v-col cols="1" >
              <v-btn
                  v-for="(individual,idx) in individuals2"
                  :key="idx"
                  @click="logout()"
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
        <v-col cols="2">
          <v-sheet color="grey lighten-3" rounded="lg">

            <v-row justify="center">
            <v-list color="transparent">


              <v-list-item>
                <v-row>
                <v-btn
                    tile
                    color="success"
                    @click="putAuthority()"
                >
                  <v-icon
                      left>
                    mdi-pencil
                  </v-icon>
                  글 수정</v-btn>

                </v-row>
              </v-list-item>

              <v-list-item>
                <v-row>
                  <v-btn
                      tile
                      color="success"
                      @click="delAuthority()"
                  >
                    <v-icon
                        left>
                      mdi-delete
                    </v-icon>
                    글 삭제</v-btn>

                </v-row>
              </v-list-item>

              <v-list-item>
                <v-row>
                  <v-btn
                      tile
                      color="success"
                      @click="addWishList()"
                  >
                    <v-icon
                        left>
                      mdi-thumb-up
                    </v-icon>
                    찜 하기</v-btn>

                </v-row>
              </v-list-item>

              <v-list-item>
                <v-row>
                  <v-btn
                      tile
                      color="success"
                      @click="linkTo5"
                  >
                    <v-icon
                        left>
                      mdi-arrow-left
                    </v-icon>
                    글 목록</v-btn>

                </v-row>
              </v-list-item>
            </v-list>
            </v-row>

          </v-sheet>
        </v-col>



    <v-card width="750" height="auto">

      <v-carousel>
        <v-carousel-item

          v-for="(data,idx) in userFile"
          :key="idx"
          :src="require('@/assets/'+ data.fileName)"
          style="object-fit: cover"

          reverse-transition="fade-transition"
          transition="fade-transition"
          >
        </v-carousel-item>
      </v-carousel>

      <v-alert
          outlined
          color="black">
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
      </v-alert>

<v-spacer vertical></v-spacer>

   <v-divider></v-divider>

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
              @click="putOpenDialog(item.cid,item.mid)"
          >
            mdi-pencil
          </v-icon>
          /
          <v-icon
              class="mr-2"
              @click="delOpenDialog(item.cid,item.mid)"
          >
            mdi-delete
          </v-icon>
        </template>
      </v-data-table>
    </v-card>

    <v-card width="750" height="auto">



    </v-card>


  <CarCommentChange
    ref="CarComment"
  >
  </CarCommentChange>

  <CarCommentDel
    ref="CommentDel">
  </CarCommentDel>

        </v-row>
      </v-container>
    </v-main>

    <v-divider></v-divider>

    <v-footer
        color="white"
        padless
    >
      <v-row
          align="center"
          justify="center"
          no-gutters
      >

        <v-btn
            text
            rounded
            class="my-2"
            @click="linkTo5"
        >
          <v-icon>
            mdi-home
          </v-icon>

        </v-btn>
        <v-btn
            text
            rounded
            class="my-2"
            @click="linkTo8"
        >
          <v-icon>
            mdi-heart
          </v-icon>

        </v-btn>

        <v-btn
            :href="`https://github.com/joo9710/CarStoreApi`" target="_blank"
            text
            rounded
            class="my-2"
        >
          <v-icon>mdi-github</v-icon>
        </v-btn>

        <v-btn
            :href="`https://youtube.com`" target="_blank"
            text
            rounded
            class="my-2"
        >
          <v-icon>mdi-youtube</v-icon>
        </v-btn>

        <v-btn
            :href="`https://www.instagram.com/`" target="_blank"
            text
            rounded
            class="my-2"
        >

          <v-icon>mdi-instagram</v-icon>

        </v-btn>

        <v-btn
            :href="`https://z00h.tistory.com/`" target="_blank"
            text
            rounded
            class="my-2"
        >
          <span>Blog</span>
        </v-btn>
      </v-row>
    </v-footer>

  </v-app>
</template>
<script>


import CarCommentChange from "@/views/CarCommentChange";
import CarCommentDel from "@/views/CarCommentDel"

export default {
  name: 'CarDetail.vue',
  components: {CarCommentDel, CarCommentChange},

  data () {
    return {
      users: '',
      userFile: '',
      fileName:'',
      cusers:[],
      cuser:'',
      link1: "Car",
      mid:this.$store.state.userStore.mid,
      nickName:this.$store.state.userStore.nickName,
      carName:'',
      content: '',
      subCarName:'',
      year:'',
      distance:'',
      area:'',
      price:'',
      thumb:'',

      loginBtnShow: false,
      wishBtnShow: false,

      headers: [
        {text: '작성자', value: 'author'},
        {text: '내용', value: 'content'},
        {text: '날짜', value: 'writeDate'},
        {text: '시간', value: 'writeTime'},
        { text: '수정  /  삭제', value: 'actions',  align: 'center',sortable: false },

      ],


      nationals: [
        {national:'국산'},
        {national:'수입'}
      ],

      individuals1: [
        {individual:'마이페이지', link:"MyPage"}
      ],

      individuals2: [
        {individual:'로그아웃', link:"Car"}
      ],

      category:'',
      keywords:['작성자', '차량명','지역'],
    }
  },

      mounted() {
        this.retrieveUsers();
        this.retrieveUsers2();
        this.showComment();
      },

      methods: {
        //댓글 수정 dialog
        putOpenDialog(cid,mid){
          console.log("dialog : " + cid)
          if(mid==this.mid){
          this.$refs.CarComment.putChildDialog(cid)
          }
          else{
            alert("댓글 수정 권한이 없습니다.");
          }
        },

        //댓글 삭제 dialog
        delOpenDialog(cid,mid){
          console.log("dialog: " + cid)
          if(mid==this.mid){
            this.$refs.CommentDel.delChildDialog(cid)
          }else{
            alert("댓글을 삭제할 수 없습니다.");
          }
        },

        linkTo(data){
          this.$router.push({name:data})
        },

        linkTo2(){
          let carId = this.$route.query.carId
          this.$router.push({name:"CarChange", query: { carId: carId }})
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

        linkTo6() {
          this.$router.push({name:"Login"})
        },

        linkTo7() {
          this.$router.push({name:"CarSearch", query: {category: this.category, keyword: this.carName, page: this.page-1, size: this.size}})
        },

        linkTo8() {
          if(this.$store.state.userStore.mid ==0) {
            alert("로그인 후 사용 바랍니다.");
          }else {
            this.$router.push({name:"WishList"});
          }
        },

        logout() {
          console.log('logout')
          this.$store.dispatch('logout')
          alert("로그아웃 처리 되었습니다.");
        },




        //글 상세내용 보여주기
        retrieveUsers() {
          let carId = this.$route.query.carId
          this.$axios.get("car/" + carId)
              .then(response => {
                this.users = response.data.data;
                console.log(response.data.data);

                if (this.$store.state.userStore.mid == 0) {
                  this.loginBtnShow = true
                  this.wishBtnShow = false
                } else {
                  this.loginBtnShow = false
                  this.wishBtnShow = true
                }

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
            console.log(this.userFile);
          })
          .catch(e => {
            console.log(e);
          })
        },

        //댓글 쓰기
        writeComment () {
          if(this.$store.state.userStore.mid==0) {
            alert('로그인 후 이용가능합니다.')
            this.$router.go(0);
          }else{
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
        }
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
        },

        //찜목록 추가하기
        addWishList() {
          if(this.mid!= 0){
          let data = {}
          data.mid = this.mid
          data.carId = this.$route.query.carId
          data.carName = this.users.carName
            data.subCarName=this.users.subCarName
          data.year =this.users.year
          data.distance =this.users.distance
          data.area =this.users.area
          data.price =this.users.price
          data.thumb =this.users.thumb

          this.$axios.post("wishlist/", JSON.stringify(data), {
            headers: {
              "Content-Type": `application/json`,
            }
          }).then(res => {
            if(res.data.success === true) {
              alert('찜목록에 추가되었습니다.')
            } else {
              alert("이미 찜목록에 추가된 차량입니다.");
            }
          })
              .catch((err)=>{
                console.log(err);
              })
        }else {
            alert("로그인 후 이용해 주세요")
          }
          },




      }
}

</script>

