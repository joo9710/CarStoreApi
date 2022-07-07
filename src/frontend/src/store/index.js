import Vue from 'vue';
import Vuex from 'vuex';
import createPersistedState from 'vuex-persistedstate';
import userStore from "@/store/modules/userStore";

Vue.use(Vuex);

export const store = new Vuex.Store({
    modules: {
        userStore: userStore
    },

    plugins: [createPersistedState({
        paths: ["userStore"]
    })]

});

export default store


