<template>
  <div>
    <v-toolbar flat extended extension-height="10%">
      <v-spacer></v-spacer>
      <div class="font-buttom-2">
        <v-btn x-large text @click="$router.push('/UserSearch')">
          <v-icon left>search</v-icon>ค้นหาข้อมูลสมาชิกประกันสุขภาพ
        </v-btn>
      </div>
    </v-toolbar>
  </div>
</template>
<style>
@import url("https://fonts.googleapis.com/css?family=Itim|Sriracha&display=swap");

.font-buttom-2 {
  font-family: "Itim", cursive;
  margin-top: 1%;
}
</style>
