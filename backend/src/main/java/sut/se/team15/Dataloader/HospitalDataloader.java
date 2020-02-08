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
		Region r01 = regionRepository.findById(4);
		p01.setRegionId(r01);
		provinceRepository.save(p01);

		Province p02 = new Province();
		p02.setProvinceName("กรุงเทพฯ");
		Region r02 = regionRepository.findById(1);
		p02.setRegionId(r02);
		provinceRepository.save(p02);

		Province p03 = new Province();
		p03.setProvinceName("กาญจนบุรี");
		Region r03 = regionRepository.findById(1);
		p03.setRegionId(r03);
		provinceRepository.save(p03);

		Province p04 = new Province();
		p04.setProvinceName("กาฬสินธุ์");
		Region r04 = regionRepository.findById(2);
		p04.setRegionId(r04);
		provinceRepository.save(p04);
		// 3
		Province p05 = new Province();
		p05.setProvinceName("กำแพงเพชร");
		Region r05 = regionRepository.findById(3);
		p05.setRegionId(r05);
		provinceRepository.save(p05);
		// 2
		Province p06 = new Province();
		p06.setProvinceName("ขอนแก่น");
		Region r06 = regionRepository.findById(2);
		p06.setRegionId(r06);
		provinceRepository.save(p06);
		// 1
		Province p07 = new Province();
		p07.setProvinceName("จันทบุรี");
		Region r07 = regionRepository.findById(1);
		p07.setRegionId(r07);
		provinceRepository.save(p07);
		// 1*
		// 1
		Province p08 = new Province();
		p08.setProvinceName("ฉะเชิงเทรา");
		Region r08 = regionRepository.findById(1);
		p08.setRegionId(r08);
		provinceRepository.save(p08);
		// 1
		Province p09 = new Province();
		p09.setProvinceName("ชลบุรี");
		Region r09 = regionRepository.findById(1);
		p09.setRegionId(r09);
		provinceRepository.save(p09);
		// 1
		Province p10 = new Province();
		p10.setProvinceName("ชัยนาท");
		Region r10 = regionRepository.findById(1);
		p10.setRegionId(r10);
		provinceRepository.save(p10);
		// 2
		Province p11 = new Province();
		p11.setProvinceName("ชัยภูมิ");
		Region r11 = regionRepository.findById(2);
		p11.setRegionId(r11);
		provinceRepository.save(p11);
		// 4
		Province p12 = new Province();
		p12.setProvinceName("ชุมพร");
		Region r12 = regionRepository.findById(4);
		p12.setRegionId(r12);
		provinceRepository.save(p12);
		// 3
		Province p13 = new Province();
		p13.setProvinceName("เชียงราย");
		Region r13 = regionRepository.findById(3);
		p13.setRegionId(r13);
		provinceRepository.save(p13);
		// 3
		Province p14 = new Province();
		p14.setProvinceName("เชียงใหม่");
		Region r14 = regionRepository.findById(3);
		p14.setRegionId(r14);
		provinceRepository.save(p14);
		// 4
		Province p15 = new Province();
		p15.setProvinceName("ตรัง");
		Region r15 = regionRepository.findById(4);
		p15.setRegionId(r15);
		provinceRepository.save(p15);
		// 1
		Province p16 = new Province();
		p16.setProvinceName("ตราด");
		Region r16 = regionRepository.findById(1);
		p16.setRegionId(r16);
		provinceRepository.save(p16);
		// 3
		Province p17 = new Province();
		p17.setProvinceName("ตาก");
		Region r17 = regionRepository.findById(3);
		p17.setRegionId(r17);
		provinceRepository.save(p17);
		// 2*
		// 1
		Province p18 = new Province();
		p18.setProvinceName("นครนายก");
		Region r18 = regionRepository.findById(1);
		p18.setRegionId(r18);
		provinceRepository.save(p18);
		// 1
		Province p19 = new Province();
		p19.setProvinceName("นครปฐม");
		Region r19 = regionRepository.findById(1);
		p19.setRegionId(r19);
		provinceRepository.save(p19);
		// 2
		Province p20 = new Province();
		p20.setProvinceName("นครพนม");
		Region r20 = regionRepository.findById(2);
		p20.setRegionId(r20);
		provinceRepository.save(p20);
		// 2
		Province p21 = new Province();
		p21.setProvinceName("นครราชสีมา");
		Region r21 = regionRepository.findById(2);
		p21.setRegionId(r21);
		provinceRepository.save(p21);
		// 4
		Province p22 = new Province();
		p22.setProvinceName("นครศรีธรรมราช");
		Region r22 = regionRepository.findById(4);
		p22.setRegionId(r22);
		provinceRepository.save(p22);
		// 3
		Province p23 = new Province();
		p23.setProvinceName("นครสวรรค์");
		Region r23 = regionRepository.findById(3);
		p23.setRegionId(r23);
		provinceRepository.save(p23);
		// 1
		Province p24 = new Province();
		p24.setProvinceName("นนทบุรี");
		Region r24 = regionRepository.findById(1);
		p24.setRegionId(r24);
		provinceRepository.save(p24);
		// 4
		Province p25 = new Province();
		p25.setProvinceName("นราธิวาส");
		Region r25 = regionRepository.findById(4);
		p25.setRegionId(r25);
		provinceRepository.save(p25);
		// 3
		Province p26 = new Province();
		p26.setProvinceName("น่าน");
		Region r26 = regionRepository.findById(3);
		p26.setRegionId(r26);
		provinceRepository.save(p26);
		// 2
		Province p27 = new Province();
		p27.setProvinceName("บึงกาฬ");
		Region r27 = regionRepository.findById(2);
		p27.setRegionId(r27);
		provinceRepository.save(p27);
		// 3*
		// 2
		Province p28 = new Province();
		p28.setProvinceName("บุรีรัมย์");
		Region r28 = regionRepository.findById(2);
		p28.setRegionId(r28);
		provinceRepository.save(p28);
		// 1
		Province p29 = new Province();
		p29.setProvinceName("ปทุมธานี");
		Region r29 = regionRepository.findById(1);
		p29.setRegionId(r29);
		provinceRepository.save(p29);
		// 1
		Province p30 = new Province();
		p30.setProvinceName("ประจวบคีรีขันธ์");
		Region r30 = regionRepository.findById(1);
		p30.setRegionId(r30);
		provinceRepository.save(p30);
		// 1
		Province p31 = new Province();
		p31.setProvinceName("ปราจีนบุรี");
		Region r31 = regionRepository.findById(1);
		p31.setRegionId(r31);
		provinceRepository.save(p31);
		// 4
		Province p32 = new Province();
		p32.setProvinceName("ปัตตานี");
		Region r32 = regionRepository.findById(4);
		p32.setRegionId(r32);
		provinceRepository.save(p32);
		// 1
		Province p33 = new Province();
		p33.setProvinceName("พระนครศรีอยุธยา");
		Region r33 = regionRepository.findById(1);
		p33.setRegionId(r33);
		provinceRepository.save(p33);
		// 3
		Province p34 = new Province();
		p34.setProvinceName("พะเยา");
		Region r34 = regionRepository.findById(3);
		p34.setRegionId(r34);
		provinceRepository.save(p34);
		// 4
		Province p35 = new Province();
		p35.setProvinceName("พังงา");
		Region r35 = regionRepository.findById(4);
		p35.setRegionId(r35);
		provinceRepository.save(p35);
		// 4
		Province p36 = new Province();
		p36.setProvinceName("พัทลุง");
		Region r36 = regionRepository.findById(4);
		p36.setRegionId(r36);
		provinceRepository.save(p36);
		// 3
		Province p37 = new Province();
		p37.setProvinceName("พิจิตร");
		Region r37 = regionRepository.findById(3);
		p37.setRegionId(r37);
		provinceRepository.save(p37);
		// 4*
		// 3
		Province p38 = new Province();
		p38.setProvinceName("พิษณุโลก");
		Region r38 = regionRepository.findById(3);
		p38.setRegionId(r38);
		provinceRepository.save(p38);
		// 1
		Province p39 = new Province();
		p39.setProvinceName("เพชรบุรี");
		Region r39 = regionRepository.findById(1);
		p39.setRegionId(r39);
		provinceRepository.save(p39);
		// 3
		Province p40 = new Province();
		p40.setProvinceName("เพชรบูรณ์");
		Region r40 = regionRepository.findById(3);
		p40.setRegionId(r40);
		provinceRepository.save(p40);
		// 3
		Province p41 = new Province();
		p41.setProvinceName("แพร่");
		Region r41 = regionRepository.findById(3);
		p41.setRegionId(r41);
		provinceRepository.save(p41);
		// 4
		Province p42 = new Province();
		p42.setProvinceName("ภูเก็ต");
		Region r42 = regionRepository.findById(4);
		p42.setRegionId(r42);
		provinceRepository.save(p42);
		// 2
		Province p43 = new Province();
		p43.setProvinceName("มหาสารคาม");
		Region r43 = regionRepository.findById(2);
		p43.setRegionId(r43);
		provinceRepository.save(p43);
		// 2
		Province p45 = new Province();
		p45.setProvinceName("มุกดาหาร");
		Region r44 = regionRepository.findById(2);
		p45.setRegionId(r44);
		provinceRepository.save(p45);
		// 3
		Province p46 = new Province();
		p46.setProvinceName("แม่ฮ่องสอน");
		Region r45 = regionRepository.findById(3);
		p46.setRegionId(r45);
		provinceRepository.save(p46);
		// 2
		Province p47 = new Province();
		p47.setProvinceName("ยโสธร");
		Region r46 = regionRepository.findById(2);
		p47.setRegionId(r46);
		provinceRepository.save(p47);
		// 4
		Province p48 = new Province();
		p48.setProvinceName("ยะลา");
		Region r47 = regionRepository.findById(4);
		p48.setRegionId(r47);
		provinceRepository.save(p48);
		// 5*
		// 2
		Province p49 = new Province();
		p49.setProvinceName("ร้อยเอ็ด");
		Region r48 = regionRepository.findById(2);
		p49.setRegionId(r48);
		provinceRepository.save(p49);
		// 4
		Province p50 = new Province();
		p50.setProvinceName("ระนอง");
		Region r49 = regionRepository.findById(4);
		p50.setRegionId(r49);
		provinceRepository.save(p50);
		// 1
		Province p51 = new Province();
		p51.setProvinceName("ระยอง");
		Region r50 = regionRepository.findById(1);
		p51.setRegionId(r50);
		provinceRepository.save(p51);
		// 1
		Province p52 = new Province();
		p52.setProvinceName("ราชบุรี");
		Region r51 = regionRepository.findById(1);
		p52.setRegionId(r51);
		provinceRepository.save(p52);
		// 1
		Province p53 = new Province();
		p53.setProvinceName("ลพบุรี");
		Region r52 = regionRepository.findById(1);
		p53.setRegionId(r52);
		provinceRepository.save(p53);
		// 3
		Province p54 = new Province();
		p54.setProvinceName("ลำปาง");
		Region r53 = regionRepository.findById(3);
		p54.setRegionId(r53);
		provinceRepository.save(p54);
		// 3
		Province p55 = new Province();
		p55.setProvinceName("ลำพูน");
		Region r54 = regionRepository.findById(3);
		p55.setRegionId(r54);
		provinceRepository.save(p55);
		// 2
		Province p56 = new Province();
		p56.setProvinceName("เลย");
		Region r55 = regionRepository.findById(2);
		p56.setRegionId(r55);
		provinceRepository.save(p56);
		// 2
		Province p57 = new Province();
		p57.setProvinceName("ศรีสะเกษ");
		Region r56 = regionRepository.findById(2);
		p57.setRegionId(r56);
		provinceRepository.save(p57);
		// 2
		Province p58 = new Province();
		p58.setProvinceName("สกลนคร");
		Region r57 = regionRepository.findById(2);
		p58.setRegionId(r57);
		provinceRepository.save(p58);
		// 6*
		// 4
		Province p59 = new Province();
		p59.setProvinceName("สงขลา");
		Region r58 = regionRepository.findById(4);
		p59.setRegionId(r58);
		provinceRepository.save(p59);
		// 4
		Province p60 = new Province();
		p60.setProvinceName("สตูล");
		Region r59 = regionRepository.findById(4);
		p60.setRegionId(r59);
		provinceRepository.save(p60);
		// 1
		Province p61 = new Province();
		p61.setProvinceName("สมุทรปราการ");
		Region r60 = regionRepository.findById(1);
		p61.setRegionId(r60);
		provinceRepository.save(p61);
		// 1
		Province p62 = new Province();
		p62.setProvinceName("สมุทรสงคราม");
		Region r61 = regionRepository.findById(1);
		p62.setRegionId(r61);
		provinceRepository.save(p62);
		// 1
		Province p63 = new Province();
		p63.setProvinceName("สมุทรสาคร");
		Region r62 = regionRepository.findById(1);
		p63.setRegionId(r62);
		provinceRepository.save(p63);
		// 1
		Province p64 = new Province();
		p64.setProvinceName("สระแก้ว");
		Region r63 = regionRepository.findById(1);
		p64.setRegionId(r63);
		provinceRepository.save(p64);
		// 1
		Province p65 = new Province();
		p65.setProvinceName("สระบุรี");
		Region r64 = regionRepository.findById(1);
		p65.setRegionId(r64);
		provinceRepository.save(p65);
		// 1
		Province p66 = new Province();
		p66.setProvinceName("สิงห์บุรี");
		Region r65 = regionRepository.findById(1);
		p66.setRegionId(r65);
		provinceRepository.save(p66);
		// 3
		Province p67 = new Province();
		p67.setProvinceName("สุโขทัย");
		Region r66 = regionRepository.findById(3);
		p67.setRegionId(r66);
		provinceRepository.save(p67);
		// 1
		Province p68 = new Province();
		p68.setProvinceName("สุพรรณบุรี");
		Region r67 = regionRepository.findById(1);
		p68.setRegionId(r67);
		provinceRepository.save(p68);
		// 7*
		// 4
		Province p69 = new Province();
		p69.setProvinceName("สุราษฎร์ธานี");
		Region r68 = regionRepository.findById(4);
		p69.setRegionId(r68);
		provinceRepository.save(p69);
		// 2
		Province p70 = new Province();
		p70.setProvinceName("สุรินทร์");
		Region r69 = regionRepository.findById(2);
		p70.setRegionId(r69);
		provinceRepository.save(p70);
		// 2
		Province p71 = new Province();
		p71.setProvinceName("หนองคาย");
		Region r70 = regionRepository.findById(2);
		p71.setRegionId(r70);
		provinceRepository.save(p71);
		// 2
		Province p72 = new Province();
		p72.setProvinceName("หนองบัวลำภู");
		Region r71 = regionRepository.findById(2);
		p72.setRegionId(r71);
		provinceRepository.save(p72);
		// 1
		Province p73 = new Province();
		p73.setProvinceName("อ่างทอง");
		Region r72 = regionRepository.findById(1);
		p73.setRegionId(r72);
		provinceRepository.save(p73);
		// 2
		Province p74 = new Province();
		p74.setProvinceName("อำนาจเจริญ");
		Region r73 = regionRepository.findById(2);
		p74.setRegionId(r73);
		provinceRepository.save(p74);
		// 2
		Province p75 = new Province();
		p75.setProvinceName("อุดรธานี");
		Region r74 = regionRepository.findById(2);
		p75.setRegionId(r74);
		provinceRepository.save(p75);
		// 3
		Province p76 = new Province();
		p76.setProvinceName("อุตรดิตถ์");
		Region r75 = regionRepository.findById(3);
		p76.setRegionId(r75);
		provinceRepository.save(p76);
		// 3
		Province p77 = new Province();
		p77.setProvinceName("อุทัยธานี");
		Region r76 = regionRepository.findById(3);
		p77.setRegionId(r76);
		provinceRepository.save(p77);
		// 2
		Province p78 = new Province();
		p78.setProvinceName("อุบลราชธานี");
		Region r77 = regionRepository.findById(2);
		p78.setRegionId(r77);
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