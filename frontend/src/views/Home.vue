<template>
  <v-app>
    <v-content>
      <ToolBars></ToolBars>
      <div>
        <v-carousel cycle hide-delimiters height="400">
          <v-carousel-item v-for="(item,i) in items" :key="i" :src="item.src"></v-carousel-item>
        </v-carousel>
      </div>

      <v-container class="my-3">
        <v-divider></v-divider>
        <v-layout row wrap justify-center>
          <v-flex sm6 md4 v-for="texts in text" :key="texts.title">
            <v-card flat class="text-xs-center ma-3">
              <v-card-text>
                <div class="font-head-desing-2">
                  <p>
                    <v-icon class="ma-2">{{ texts.icon }}</v-icon>
                    {{texts.title}}
                  </p>
                </div>
                <div class="font-desing-3">{{texts.text}}</div>
              </v-card-text>
            </v-card>
          </v-flex>
        </v-layout>
      </v-container>
    </v-content>
  </v-app>
</template>

<script>
import ToolBars from "../components/ToolBars";

export default {
  name: "Home",
  components: {
    ToolBars
  },
  data() {
    return {
      items: [
        {
          src: require("../assets/slide/slide-1.jpg")
        },
        {
          src: require("../assets/slide/slide-2.jpg")
        },
        {
          src: require("../assets/slide/slide-3.jpg")
        },
        {
          src: require("../assets/slide/slide-4.jpg")
        }
      ],
      text: [
        {
          icon: "class",
          title: "ที่มาและความสำคัญ",
          text:
            "สืบเนื่องจากปัจจุบันการจะสมัครสมาชิกประกันสุขภาพนั้นจำเป็นต้องมีเอกสารหลายอย่าง จึงทำให้เกิดความยุ่งยากแก่ผู้ที่สนใจจะสมัครสมาชิกประกันสุขภาพขึ้น เราได้เล็งเห็นและจึงพัฒนาระบบประกันสุขภาพนี่ขึ้นมา"
        },
        {
          icon: "help",
          title: "เกี่ยวกับ",
          text:
            "ระบบประกันสุขภาพเป็นระบบที่ค่อยอำนวยความสะดวกให้แก่สมาชิกประกันสุขภาพ สมาชิกประกันสุขภาพสามารถสมัครกรมธรรม์ ขอใช้สิทธิ์กรมธรรม์ และเปลี่ยนแปลงกรมธรรม์ได้"
        },
        {
          icon: "room",
          title: "ติดต่อ",
          text:
            "สาขาวิชาวิศวกรรมคอมพิวเตอร์ สำนักวิชาวิศวกรรมศาสตร์ มหาวิทยาลัยเทคโนโลยีสุรนารี 111, ถนน มหาวิทยาลัย ตำบล สุรนารี อำเภอเมืองนครราชสีมา นครราชสีมา 30000"
        }
      ]
    };
  }
};
</script>

<style>
.card-desing-1 {
  margin-top: 0%;
  width: auto;
}
.font-head-desing-2 {
  font-family: "Sriracha", cursive;
  font-weight: bold;
  font-size: 20px;
}
.font-desing-3 {
  font-family: "Itim", cursive;
  font-size: 16px;
  margin-top: 5%;
}
</style>