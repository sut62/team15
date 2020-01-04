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

		Stream.of("กระบี่", "กรุงเทพฯ", "กาญจนบุรี", "กาฬสินธุ์", "กำแพงเพชร", "ขอนแก่น", "จันทบุรี", "ฉะเชิงเทรา",
				"ชลบุรี", "ชัยนาท", "ชัยภูมิ", "ชุมพร", "เชียงราย", "เชียงใหม่", "ตรัง", "ตราด", "ตาก", "นครนายก",
				"นครปฐม", "นครพนม", "นครราชสีมา", "นครศรีธรรมราช", "นครสวรรค์", "นนทบุรี", "นราธิวาส", "น่าน", "บึงกาฬ",
				"บุรีรัมย์", "ปทุมธานี", "ประจวบคีรีขันธ์", "ปราจีนบุรี", "ปัตตานี", "พระนครศรีอยุธยา", "พะเยา",
				"พังงา", "พัทลุง", "พิจิตร", "พิษณุโลก", "เพชรบุรี", "เพชรบูรณ์", "แพร่", "ภูเก็ต", "มหาสารคาม",
				"มุกดาหาร", "แม่ฮ่องสอน", "ยโสธร", "ยะลา", "ร้อยเอ็ด", "ระนอง", "ระยอง", "ราชบุรี", "ลพบุรี", "ลำปาง",
				"ลำพูน", "เลย", "ศรีสะเกษ", "สกลนคร", "สงขลา", "สตูล", "สมุทรปราการ", "สมุทรสงคราม", "สมุทรสาคร",
				"สระแก้ว", "สระบุรี", "สิงห์บุรี", "สุโขทัย", "สุพรรณบุรี", "สุราษฎร์ธานี", "สุรินทร์", "หนองคาย",
				"หนองบัวลำภู", "อ่างทอง", "อำนาจเจริญ", "อุดรธานี", "อุตรดิตถ์", "อุทัยธานี", "อุบลราชธานี")
				.forEach(provinces -> {
					Province provincedb = new Province();
					provincedb.setProvinceName(provinces);
					provinceRepository.save(provincedb);
				});
		

		hospitalTypeRepository.findAll().forEach(System.out::println);
		regionRepository.findAll().forEach(System.out::println);
		provinceRepository.findAll().forEach(System.out::println);

	};
}