<template lang="">
    <v-app-bar color="primary" app dark height="64">
        <v-app-bar-nav-icon @click="navigation_drawer = !navigation_drawer"/>
        <v-btn @click="goHome">
            <v-toolbar-title class="text-uppercase text--darken-4">
                <span>EDDI</span>
            </v-toolbar-title>
        </v-btn>
        <v-spacer></v-spacer>

        <v-menu>
            <template v-slot:activator="{ props }">
                <v-btn color="white" v-bind="props">
                    <b>Activator Slot</b>
                </v-btn>
            </template>
            <v-list>
                <v-list-item v-for="(item, index) in items" :key="index" :value="index">
                    <v-list-item-title>{{ item.title }}</v-list-item-title>
                </v-list-item>
            </v-list>
        </v-menu>

        <v-btn text @click="clickToggle">
            <span>클릭 토글</span>
            <v-icon right>mdi-hand-back-left-outline</v-icon>
        </v-btn>
        <v-btn v-if="!isLogin" text @click="signUp">
            <span>회원가입</span>
            <v-icon right>mdi-account-plus-outline</v-icon>
        </v-btn>
        <v-btn v-if="!isLogin" text @click="signIn">
            <span>로그인</span>
            <v-icon right>mdi-login</v-icon>
        </v-btn>
        <v-btn v-if="isLogin" text @click="signOut">
            <span>로그아웃</span>
            <v-icon right>mdi-exit-to-app</v-icon>
        </v-btn>
    </v-app-bar>
</template>

<script>
import 'vuetify/dist/vuetify.min.css'
import { mapGetters, useStore } from 'vuex';

export default {
    data () {
        return {
            navigation_drawer: false,
            isLogin: false,
            items: [
                { title: '클릭해봐!' },
                { title: '클릭하지마!' },
                { title: '이렇게 나오지' },
                { title: '요로코롬' },
            ]
        }
    },
    computed: {
        ...mapGetters('authenticationModule', {
            getAccessToken: 'getAccessToken',
        }),
    },
    inject: ['eventBus', 'authEventBus'],
    mounted () {
        console.log('Navigation onMounted: ' + this.authEventBus);
        const authStore = useStore()
        console.log('authStore: ' + authStore)
        console.log('accessToken: ' + this.getAccessToken)
        console.log('authStore.state.authenticationModule: ' + authStore.state.authenticationModule)

        this.authEventBus.on("login-complete", (data) => {
            console.log('로그인 완료 - 전달된 토큰: ' + data)
        })
        // this.acquireAccessToken();
    },
    methods: {
        // acquireAccessToken() {
        //     const accessToken = this.getAccessToken;
        //     console.log('accessToken 값:', accessToken);
        // },
        clickToggle () {
            alert('토글')
        },
        signUp () {
            alert('회원 가입')
        },
        signIn () {
            // Container App -> Navigation App -> Auth App
            // Navigation App에서 Auth App을 remotes로 등록해야함 (앞서 Counter App으로 컨셉 검증 완료)
            // 근데 이렇게 만들 필요가 있나 ?
            // 이 버튼 클릭하는 것을 감지해서 Event Issuing 하고
            // 해당 Event가 발행되면 Container가 응답하도록 구성하는게 더 좋을듯하다.
            
            //this.isLogin = true
            // 이 부분을 AuthApp의 getters를 사용해서 참 거짓 여부 판정하게 만든다.
            this.eventBus.emit('sign-in', 'signIn() 요청 완료')
            alert('로그인')
        },
        signOut () {
            this.isLogin = false
            alert('로그아웃')
        },
        gotoHome () {
            alert('고 홈')
        }
    }
}
</script>

<style scoped lang="scss">
#data-v-app {
    height: 64px;
}
.app-container {
    height: 64px;
}
.main-container {
    max-height: 100%;
    overflow-y: auto;
}
.v-application--wrap {
    height: 64px !important;
}
.v-application {
    height: 64px;
}
.v-main {
    height: 64px;
}
.v-application__wrap {
    > .v-app-bar {
        height: 64px;
    }
}
</style>