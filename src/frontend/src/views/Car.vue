<template>
  <div>

    <v-card width="100vw" height="1500px">


      <v-text-field
          v-model="search"
          append-icon="mdi-magnify"
          label="Search"
          single-line
          hide-details
      ></v-text-field>

      <v-data-table
          :headers="headers"
          :items="users"
          :search="search"
          :items-per-page="5"
          class="elevation-1"
      >

        <template v-slot:top>
          <v-toolbar
              flat
          >

            <v-btn @click="linkTo(link1)">글 쓰기</v-btn>

            <v-spacer></v-spacer>

            <v-btn @click="linkTo(link2)">로그인</v-btn>
            <v-btn @click="logout">로그아웃</v-btn>
          </v-toolbar>



        </template>

        <template v-slot:item.thumb="{ item }">
          <img alt="" :src="item.thumb"/>
        </template>

        <template v-slot:item.content="{ item }">
          <v-row>
            <v-col cols="12" class="pa-0">
              <router-link :to="{ name: 'CarDetail', query:{ carId: item.carId } }">
                {{item.carName}}
              </router-link>

            </v-col>

            <v-col cols="12" class="pa-0">
              <span>{{item.year}}</span>
              <span>{{item.distance}}</span>
              <span>{{item.fuel}}</span>
              <span>{{item.area}}</span>
            </v-col>
          </v-row>

        </template>
      </v-data-table>
    </v-card>
  </div>

</template>

<script>
export default {
  name: "Car",

  data: () => ({
      users: [],
    search: '',
    link1:"CarWrite",
    link2:"Login",
      headers: [
        { text: '사진', value: 'thumb' },
        { text: '차량정보', value: 'content' },
        { text: '가격', value: 'price',  align: 'center',sortable: false },
      ],

  }),

  methods: {

    retrieveUsers() {
      this.$axios.get("car/",
          {
            headers: {
              Authorization: "Bearer " + "eyJhbGciOiJIUzUxMiJ9.eyJleHAiOjE2NTY5NTEwMDUsImlhdCI6MTY1NjkzMzAwNSwianRpIjoiMTIzQG5hdmVyLmNvbSJ9.MOAvpcz0nDdXcCCKiR4tuMEWlhAD1_5OmLCBhxs1n015vd8p3dL44nLVy_lrS_pLOZqOepXVUQDifnIkK3XSTg"
            }
          })
          .then(response => {
            this.users = response.data;
            console.log(response.data);
          })
          .catch(e => {
            console.log(e);
          })
    },

    linkTo(data){
      this.$router.push({name: data})
    },

    logout(){
      console.log('logout')
      this.$store.dispatch('logout')
    }

  },

  mounted() {
    this.retrieveUsers();
  },

}

</script>

