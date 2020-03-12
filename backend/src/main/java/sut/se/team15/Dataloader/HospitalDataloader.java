package sut.se.team15.Dataloader;

import java.util.stream.Stream;

import sut.se.team15.Entity.*;
import sut.se.team15.Repository.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class HospitalDataloader implements ApplicationRunner {

	@Autowired
	private HospitalRepository hospitalRepository;
	
	@Autowired
	private HospitalTypeRepository hospitalTypeRepository;
	@Autowired
	private ProvinceRepository provinceRepository;
	@Autowired
	private RegionRepository regionRepository;

	@Override
	public void run(ApplicationArguments args) throws Exception {

		Stream.of("โรงพยาบาลเอกชน", "โรงพยาบาลรัฐ").forEach(types -> {
			HospitalType typedb = new HospitalType();
			typedb.setTypeName(types);
			hospitalTypeRepository.save(typedb);
		});

		Stream.of("ภาคกลาง", "ภาคตะวันออกเฉียงเหนือ", "ภาคเหนือ", "ภาคใต้").forEach(regions -> {
			Region regiondb = new Region();
			regiondb.setRegionName(regions);
			regionRepository.save(regiondb);
		});

		Province p01 = new Province();
		p01.setProvinceName("กระบี่");
		provinceRepository.save(p01);

		Province p02 = new Province();
		p02.setProvinceName("กรุงเทพฯ");
		provinceRepository.save(p02);

		Province p03 = new Province();
		p03.setProvinceName("กาญจนบุรี");
		provinceRepository.save(p03);

		Province p04 = new Province();
		p04.setProvinceName("กาฬสินธุ์");
		provinceRepository.save(p04);
		// 3
		Province p05 = new Province();
		p05.setProvinceName("กำแพงเพชร");
		provinceRepository.save(p05);
		// 2
		Province p06 = new Province();
		p06.setProvinceName("ขอนแก่น");
		provinceRepository.save(p06);
		// 1
		Province p07 = new Province();
		p07.setProvinceName("จันทบุรี");
		provinceRepository.save(p07);
		// 1*
		// 1
		Province p08 = new Province();
		p08.setProvinceName("ฉะเชิงเทรา");
		provinceRepository.save(p08);
		// 1
		Province p09 = new Province();
		p09.setProvinceName("ชลบุรี");
		provinceRepository.save(p09);
		// 1
		Province p10 = new Province();
		p10.setProvinceName("ชัยนาท");
		provinceRepository.save(p10);
		// 2
		Province p11 = new Province();
		p11.setProvinceName("ชัยภูมิ");
		provinceRepository.save(p11);
		// 4
		Province p12 = new Province();
		p12.setProvinceName("ชุมพร");
		provinceRepository.save(p12);
		// 3
		Province p13 = new Province();
		p13.setProvinceName("เชียงราย");
		provinceRepository.save(p13);
		// 3
		Province p14 = new Province();
		p14.setProvinceName("เชียงใหม่");
		provinceRepository.save(p14);
		// 4
		Province p15 = new Province();
		p15.setProvinceName("ตรัง");
		provinceRepository.save(p15);
		// 1
		Province p16 = new Province();
		p16.setProvinceName("ตราด");
		provinceRepository.save(p16);
		// 3
		Province p17 = new Province();
		p17.setProvinceName("ตาก");
		provinceRepository.save(p17);
		// 2*
		// 1
		Province p18 = new Province();
		p18.setProvinceName("นครนายก");
		provinceRepository.save(p18);
		// 1
		Province p19 = new Province();
		p19.setProvinceName("นครปฐม");
		provinceRepository.save(p19);
		// 2
		Province p20 = new Province();
		p20.setProvinceName("นครพนม");
		provinceRepository.save(p20);
		// 2
		Province p21 = new Province();
		p21.setProvinceName("นครราชสีมา");
		provinceRepository.save(p21);
		// 4
		Province p22 = new Province();
		p22.setProvinceName("นครศรีธรรมราช");
		provinceRepository.save(p22);
		// 3
		Province p23 = new Province();
		p23.setProvinceName("นครสวรรค์");
		provinceRepository.save(p23);
		// 1
		Province p24 = new Province();
		p24.setProvinceName("นนทบุรี");
		provinceRepository.save(p24);
		// 4
		Province p25 = new Province();
		p25.setProvinceName("นราธิวาส");
		provinceRepository.save(p25);
		// 3
		Province p26 = new Province();
		p26.setProvinceName("น่าน");
		provinceRepository.save(p26);
		// 2
		Province p27 = new Province();
		p27.setProvinceName("บึงกาฬ");
		provinceRepository.save(p27);
		// 3*
		// 2
		Province p28 = new Province();
		p28.setProvinceName("บุรีรัมย์");
		provinceRepository.save(p28);
		// 1
		Province p29 = new Province();
		p29.setProvinceName("ปทุมธานี");
		provinceRepository.save(p29);
		// 1
		Province p30 = new Province();
		p30.setProvinceName("ประจวบคีรีขันธ์");
		provinceRepository.save(p30);
		// 1
		Province p31 = new Province();
		p31.setProvinceName("ปราจีนบุรี");
		provinceRepository.save(p31);
		// 4
		Province p32 = new Province();
		p32.setProvinceName("ปัตตานี");
		provinceRepository.save(p32);
		// 1
		Province p33 = new Province();
		p33.setProvinceName("พระนครศรีอยุธยา");
		provinceRepository.save(p33);
		// 3
		Province p34 = new Province();
		p34.setProvinceName("พะเยา");
		provinceRepository.save(p34);
		// 4
		Province p35 = new Province();
		p35.setProvinceName("พังงา");
		provinceRepository.save(p35);
		// 4
		Province p36 = new Province();
		p36.setProvinceName("พัทลุง");
		provinceRepository.save(p36);
		// 3
		Province p37 = new Province();
		p37.setProvinceName("พิจิตร");
		provinceRepository.save(p37);
		// 4*
		// 3
		Province p38 = new Province();
		p38.setProvinceName("พิษณุโลก");
		provinceRepository.save(p38);
		// 1
		Province p39 = new Province();
		p39.setProvinceName("เพชรบุรี");
		provinceRepository.save(p39);
		// 3
		Province p40 = new Province();
		p40.setProvinceName("เพชรบูรณ์");
		provinceRepository.save(p40);
		// 3
		Province p41 = new Province();
		p41.setProvinceName("แพร่");
		provinceRepository.save(p41);
		// 4
		Province p42 = new Province();
		p42.setProvinceName("ภูเก็ต");
		provinceRepository.save(p42);
		// 2
		Province p43 = new Province();
		p43.setProvinceName("มหาสารคาม");
		provinceRepository.save(p43);
		// 2
		Province p45 = new Province();
		p45.setProvinceName("มุกดาหาร");
		provinceRepository.save(p45);
		// 3
		Province p46 = new Province();
		p46.setProvinceName("แม่ฮ่องสอน");
		provinceRepository.save(p46);
		// 2
		Province p47 = new Province();
		p47.setProvinceName("ยโสธร");
		provinceRepository.save(p47);
		// 4
		Province p48 = new Province();
		p48.setProvinceName("ยะลา");
		provinceRepository.save(p48);
		// 5*
		// 2
		Province p49 = new Province();
		p49.setProvinceName("ร้อยเอ็ด");
		provinceRepository.save(p49);
		// 4
		Province p50 = new Province();
		p50.setProvinceName("ระนอง");
		provinceRepository.save(p50);
		// 1
		Province p51 = new Province();
		p51.setProvinceName("ระยอง");
		provinceRepository.save(p51);
		// 1
		Province p52 = new Province();
		p52.setProvinceName("ราชบุรี");
		provinceRepository.save(p52);
		// 1
		Province p53 = new Province();
		p53.setProvinceName("ลพบุรี");
		provinceRepository.save(p53);
		// 3
		Province p54 = new Province();
		p54.setProvinceName("ลำปาง");
		provinceRepository.save(p54);
		// 3
		Province p55 = new Province();
		p55.setProvinceName("ลำพูน");
		provinceRepository.save(p55);
		// 2
		Province p56 = new Province();
		p56.setProvinceName("เลย");
		provinceRepository.save(p56);
		// 2
		Province p57 = new Province();
		p57.setProvinceName("ศรีสะเกษ");
		provinceRepository.save(p57);
		// 2
		Province p58 = new Province();
		p58.setProvinceName("สกลนคร");
		provinceRepository.save(p58);
		// 6*
		// 4
		Province p59 = new Province();
		p59.setProvinceName("สงขลา");
		provinceRepository.save(p59);
		// 4
		Province p60 = new Province();
		p60.setProvinceName("สตูล");
		provinceRepository.save(p60);
		// 1
		Province p61 = new Province();
		p61.setProvinceName("สมุทรปราการ");
		provinceRepository.save(p61);
		// 1
		Province p62 = new Province();
		p62.setProvinceName("สมุทรสงคราม");
		provinceRepository.save(p62);
		// 1
		Province p63 = new Province();
		p63.setProvinceName("สมุทรสาคร");
		provinceRepository.save(p63);
		// 1
		Province p64 = new Province();
		p64.setProvinceName("สระแก้ว");
		provinceRepository.save(p64);
		// 1
		Province p65 = new Province();
		p65.setProvinceName("สระบุรี");
		provinceRepository.save(p65);
		// 1
		Province p66 = new Province();
		p66.setProvinceName("สิงห์บุรี");
		provinceRepository.save(p66);
		// 3
		Province p67 = new Province();
		p67.setProvinceName("สุโขทัย");
		provinceRepository.save(p67);
		// 1
		Province p68 = new Province();
		p68.setProvinceName("สุพรรณบุรี");
		provinceRepository.save(p68);
		// 7*
		// 4
		Province p69 = new Province();
		p69.setProvinceName("สุราษฎร์ธานี");
		provinceRepository.save(p69);
		// 2
		Province p70 = new Province();
		p70.setProvinceName("สุรินทร์");
		provinceRepository.save(p70);
		// 2
		Province p71 = new Province();
		p71.setProvinceName("หนองคาย");
		provinceRepository.save(p71);
		// 2
		Province p72 = new Province();
		p72.setProvinceName("หนองบัวลำภู");
		provinceRepository.save(p72);
		// 1
		Province p73 = new Province();
		p73.setProvinceName("อ่างทอง");
		provinceRepository.save(p73);
		// 2
		Province p74 = new Province();
		p74.setProvinceName("อำนาจเจริญ");
		provinceRepository.save(p74);
		// 2
		Province p75 = new Province();
		p75.setProvinceName("อุดรธานี");
		provinceRepository.save(p75);
		// 3
		Province p76 = new Province();
		p76.setProvinceName("อุตรดิตถ์");
		provinceRepository.save(p76);
		// 3
		Province p77 = new Province();
		p77.setProvinceName("อุทัยธานี");
		provinceRepository.save(p77);
		// 2
		Province p78 = new Province();
		p78.setProvinceName("อุบลราชธานี");
		provinceRepository.save(p78);

		Hospital hospital = new Hospital();
		hospital.setHospitalName("SUT Smart Hospital");
		hospital.setLocationDetails("111, ถนน มหาวิทยาลัย ตำบล สุรนารี อำเภอเมืองนครราชสีมา นครราชสีมา 30000");
		hospital.setTelephoneNumber("0424415678");

		HospitalType hospitalType = hospitalTypeRepository.findById(2);
		Region region = regionRepository.findById(2);
		Province province = provinceRepository.findById(21);

		hospital.setHospitalTypeId(hospitalType);
		hospital.setRegionId(region);
		hospital.setProvinceId(province);
		hospitalRepository.save(hospital);

		hospitalTypeRepository.findAll().forEach(System.out::println);
		regionRepository.findAll().forEach(System.out::println);
		provinceRepository.findAll().forEach(System.out::println);

	};
}