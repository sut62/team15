<template>
  <v-app>
    <div class="img-background"
        v-bind:style="{'background-image': 'url(' + require('../assets/MainLogin.jpg') + ')'}" >
      <v-container>
        <v-layout justify-center>
          <v-card class="card-desing-3" outlined>
            <v-container fluid>
              <v-row justify="center">
                <v-col cols="12">
                  <v-row>
                  <v-col cols="12" sm="12">
                    <div class="text-center">
                      <v-btn rounded color="indigo" outlined block dark @click="$router.push('/SingInAdmin')">
                        <div class="font-desing">
                          <span>ผู้ดูแลระบบ</span>
                        </div>
                      </v-btn>
                      <div class="mt-2">
                      <span class="font-desing">หรือ</span></div>
                      <v-btn class="mt-2" rounded color="indigo" block dark @click="$router.push('/UserLogin')" >
                        <div class="font-desing">
                          <span>สมาชิกทั่วไป</span>
                        </div>
                      </v-btn>
                    </div>
                  </v-col>
                  </v-row>
                </v-col>
              </v-row>
            </v-container>
          </v-card>
        </v-layout>
      </v-container>
    </div>
  </v-app>
</template>

<script>
export default {};
</script>
<style>
.card-desing-3 {
  margin-top: 20%;
  width: 30%;
  background-color: rgba(255, 255, 255, 0.8) !important;
}
.font-desing {
  font-family: "Itim", cursive;
}
</style>