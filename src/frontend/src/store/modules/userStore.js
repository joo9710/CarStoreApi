import axios from "axios";


const userStore = {
    state: {
        userId: '',
        token: '',
        fullName:'',
        email:'',
        mid:'',
        nickName:'',
        area:'',
        fileName:'',
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
            console.log("mid state : " + data.mid)
        },

        putImage: function (state, data) {
            state.carId = data.carId
            state.fileName = require('@/assets' + data.fileName)
            console.log("carId state : " + data.carId)
            console.log("carId state : " + state.fileName)
        },

        initData : function(state){
            state.userId = ''
                state.token= ''
                state.fullName=''
                state.email=''
                state.mid=''
                state.nickName=''
                state.area=''
                state.fileName=''
            console.log("logout state : " + state.mid)
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
                    data.fullName = res.data.fullName
                    data.nickName = res.data.nickName
                    data.area = res.data.area
                    data.mid = res.data.mid
                    commit('putUserInfo', data)
                })
                .catch((error)=>{
                    console.log(error.res)
                })
        },

        uploadImage: function ({commit}, payload) {
            let data = {};
            data.carId = payload.carId;
            data.fileName = payload.fileName;

            return new Promise((resolve) => {
                setTimeout(() => {
                    commit('putImage', data);
                    resolve()
                    }, 1000)
            })
        },

        logout({commit}){
            axios.post("/logout")
                .then(() => {
                    return new Promise((resolve) => {
                        setTimeout(() => {
                            commit('initData');
                            resolve()
                        }, 1000)
                    })
                })
                .catch((err) =>{
                    console.log(err.response);
                })
        }
    },
}

export default userStore
