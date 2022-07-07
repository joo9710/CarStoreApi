import axios from "axios";


const userStore = {
    state: {
        userId: '',
        token: '',
        email:'',
        mid:'',
        nickName:'',
        area:'',
    },
    mutations: {
        loginData: function (state, data) {
            console.log("store : " + data)
            state.token = data.token
            state.email = data.email
            console.log("state : " + data.token)
            console.log("email state : " + data.email)
        },
        resetState: function (state){
          console.log(state)
        },
        putUserInfo: function (state, data) {
            state.nickName = data.nickName
            state.area = data.area
            state.mid = data.mid
            console.log("nickName state : " + data.nickName)
        },

        //loginCheck: function (state) {
          //  if (!state.token) {
            //    router.push({
              //      name: 'login'
                //}).catch(error => {
                  //  console.log(error)
               // })
           // }
       // }

    },

    actions: {
        login ({commit, dispatch}, payload){
            console.log("store :: " + payload)
            let data = {};
            data.email = JSON.parse(atob(payload.token.split('.')[1])).jti;
            data.token = payload.token
            commit('loginData', data)
            dispatch('getUserInfo', data.email)
        },

        getUserInfo({commit}, context){
            let data={};
            data.email = context
            axios.post('/user/info', JSON.stringify({"email":data.email}), {
                headers: {
                    "Content-Type": `application/json`,
                },
            })
                .then((res) => {
                    console.log(res.data)
                    data.nickName = res.data.nickName
                    data.area = res.data.area
                    data.mid = res.data.mid
                    commit('putUserInfo', data)
                })
                .catch((error)=>{
                    console.log(error.res)
                })
        }
    },
}

export default userStore
