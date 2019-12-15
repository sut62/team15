<template>
  <div>
    <ToolBars></ToolBars>
    <v-app>
      <v-content>
        <div
          class="img-background"
          v-bind:style="{'background-image': 'url(' + require('../assets/background-1.jpg') + ')'}"
        >
          <v-container>
            <v-layout justify-center>
              <v-card class="card-desing" outlined>
                <div class="color-theme">
                  <v-card-title class="white--text">
                    <div class="font-desing">
                      <span>ขอใช้สิทธิ์กรมธรรม์</span>
                    </div>
                  </v-card-title>
                </div>
                <v-container fluid>
                  <v-row justify="center">
                    <v-col cols="12">
                      <v-row>
                        <v-col cols="6" sm="6">
                          <v-select :items="items" label="กรมธรรม์" outlined></v-select>
                        </v-col>

                        <v-col cols="6" sm="6">
                          <v-select :items="items" label="โรงพยาบาลที่จะขอใช้สิทธิ์" outlined></v-select>
                        </v-col>
                      </v-row>

                      <v-row>
                        <v-col cols="6" sm="6">
                          <v-menu
                            v-model="menu"
                            :close-on-content-click="false"
                            :nudge-right="40"
                            transition="scale-transition"
                            offset-y
                            min-width="290px"
                          >
                            <template v-slot:activator="{ on }">
                              <v-text-field
                                v-model="date"
                                label="วันที่ขอใช้สิทธิ์"
                                prepend-icon="event"
                                readonly
                                v-on="on"
                              ></v-text-field>
                            </template>
                            <v-date-picker v-model="date" @input="menu = false"></v-date-picker>
                          </v-menu>
                        </v-col>

                        <v-col cols="6" sm="6">
                          <v-select :items="items" label="จุดประสงค์" outlined></v-select>
                        </v-col>
                      </v-row>

                      <v-row>
                        <v-col cols="6" sm="6">
                          <v-text-field label="ชื่อ"></v-text-field>
                        </v-col>
                      </v-row>
                    </v-col>
                  </v-row>

                  <div class="text-center">
                    <v-btn class="ma-2" rounded color="warning" dark>
                      <div class="font-desing">
                        <span>ย้อยกลับ</span>
                      </div>
                    </v-btn>
                    <v-btn class="ma-2" rounded color="primary" dark>
                      <div class="font-desing">
                        <span>ยันยันการขอใช้สิทธิ์</span>
                      </div>
                    </v-btn>
                  </div>

                </v-container>
              </v-card>
            </v-layout>
          </v-container>
        </div>
      </v-content>
    </v-app>
  </div>
</template>

<script>
import ToolBars from "../components/ToolBars";
export default {
  components: {
    ToolBars
  },
  data: () => ({
    date: new Date().toISOString().substr(0, 10),
    menu: false
  })
};
</script>

<style>
@import url("https://fonts.googleapis.com/css?family=Fredoka+One|Kanit&display=swap");

.img-background {
  width: 100%;
  height: 100%;
  background-size: cover;
}
.card-desing {
  margin-top: 7%;
  width: 60%;
  background-color: rgba(255, 255, 255, 0.5) !important;
  border-color: white !important;
}
.card-desing label {
  font-family: "Kanit", sans-serif;
}
.color-theme{
  background-color:rgb(211, 17, 69);
}
.font-desing {
  font-family: "Kanit", sans-serif;
}
</style>