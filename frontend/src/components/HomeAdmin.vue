<template>
  <div>
    <v-toolbar flat height="100px" tile color="deep-purple">
      <div height="110px">
      <v-img  dark src="../assets/Test1.png"></v-img></div>
      <v-spacer></v-spacer>
       <v-menu bottom left>
        <template v-slot:activator="{ on }">
          <v-btn dark icon v-on="on">
            <v-icon>mdi-dots-vertical</v-icon>
          </v-btn>
        </template>

         <v-card height="mx-auto" width="250" dark>
          <v-list>
            <v-list-item @click="$router.push('/HomeAdmin')">
              <v-col class="8">
                <v-icon>home</v-icon>
              </v-col>
              <v-list-item-title>Home</v-list-item-title>
            </v-list-item>
                       <v-list-item @click="$router.push('/')">
              <v-col class="8">
                <v-icon>exit_to_app</v-icon>
              </v-col>
              <v-list-item-title>Sign Out</v-list-item-title>
            </v-list-item>
             <v-divider></v-divider>
            <v-list-item @click="$router.push('/Hospital')">
              <v-col class="8">
                <v-icon>apartment</v-icon>
              </v-col>
              <v-list-item-title>Hospital</v-list-item-title>
            </v-list-item>
            <v-list-item @click="$router.push('/HospitalSearch')">
              <v-col class="8">
                <v-icon>search</v-icon>
              </v-col>
              <v-list-item-title>HospitalSearch</v-list-item-title>
            </v-list-item>
            <v-list-item @click="$router.push('/CreateInsurance')">
              <v-col class="8">
                <v-icon>add_to_photos</v-icon>
              </v-col>
              <v-list-item-title>CreateInsurance</v-list-item-title>
            </v-list-item>
            <v-list-item @click="$router.push('/PayInsurance')">
              <v-col class="8">
                <v-icon>call_to_action</v-icon>
              </v-col>
              <v-list-item-title>PayInsurance</v-list-item-title>
            </v-list-item>
             <v-list-item @click="$router.push('/Promotion')">
              <v-col class="8">
                <v-icon>mdi-message-text</v-icon>
              </v-col>
              <v-list-item-title>Promotion</v-list-item-title>
            </v-list-item>
            <v-list-item @click="$router.push('/RenewInsurance')">
              <v-col class="8">
                <v-icon>event</v-icon>
              </v-col>
              <v-list-item-title>RenewInsurance</v-list-item-title>
            </v-list-item>
          </v-list>
        </v-card>
      </v-menu>
    </v-toolbar>

    <v-carousel height="600" whight="1165" tile cycle hide-delimiter-background show-arrows-on-hover>
      <v-carousel-item v-for="(item,i) in items" :key="i" :src="item.src"></v-carousel-item>
    </v-carousel>
  </div>
</template>

<script>
export default {
  data() {
    return {
      items: [
        {
          src: require("../assets/Slide-1.jpg")
        },
        {
          src: require("../assets/Slide-2.jpg")
        },
        {
          src: require("../assets/Slide-3.jpg")
        }
      ]
    };
  }
};
</script>