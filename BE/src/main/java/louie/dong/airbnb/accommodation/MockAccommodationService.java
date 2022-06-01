package louie.dong.airbnb.accommodation;

import java.util.List;
import louie.dong.airbnb.accommodation.dto.AccommodationDetailPriceRequest;
import louie.dong.airbnb.accommodation.dto.AccommodationDetailPriceResponse;
import louie.dong.airbnb.accommodation.dto.AccommodationDetailResponse;
import louie.dong.airbnb.accommodation.dto.AccommodationPriceResponse;
import louie.dong.airbnb.accommodation.dto.AccommodationResponse;
import louie.dong.airbnb.accommodation.dto.AccommodationSearchRequest;
import louie.dong.airbnb.accommodation.dto.AccommodationSearchResponse;
import org.springframework.data.geo.Point;
import org.springframework.stereotype.Service;

@Service
public class MockAccommodationService {

	public AccommodationPriceResponse findPrices(String country) {
		return new AccommodationPriceResponse(165556, List.of(12345, 56780, 12455, 12350));
	}

	public AccommodationDetailResponse findById(Long id) {
		return new AccommodationDetailResponse(1L,
			"숙소 이름",
			"https://s3-alpha-sig.figma.com/img/2b24/5101/2265996f97a70986d95f1d6fdd40009f?Expires=1655078400&Signature=D2aMRMQGvcfCORWvl9jrqgr6Rubf2044ovniztRQgTreOhAr8yzz~xQ7m0FHd1NC9DD0Z1C69F8XAODD44kfW9NDc~EHKhjdNzgBdgWZnYZRnB41avlSb2auh6ynKlaKD8yPs-St-sgrNy9QANbQguoDmDLixQXMRI1osY8zYNc4QPtUonohHD5Td71wqsLzMAWqSWsqWq5YquBmVG8iZ3nc9KjiujFSWhotD7bt0SpwoUl59w0WeuO~G~sSU7ns1FexmtPW8KYL~UIyiVXGGux23EqwOIIOZJRBWXkoSasdpczl0l4gwaXHDkpbFWSDE7lmc23B7waBAsF2I9SraA__&Key-Pair-Id=APKAINTVSUGEWH5XD5UA",
			4.80,
			127,
			"서초구, 서울, 한국",
			"Jong",
			"https://user-images.githubusercontent.com/92966772/171337583-5428b133-eea4-4f02-9f27-7a53a414842f.png",
			new RoomInformation(3, RoomType.APARTMENT, 1, 1),
			"강남역 5번 출구에서 도보로 이동 가능합니다. 지하철, 버스 노선이 다양하고 맛집, 마트 등 주변 시설이 풍부합니다.",
			82953,
			false
		);
	}

	public AccommodationDetailPriceResponse findDetailPrice(Long id, AccommodationDetailPriceRequest accommodationRequest) {
		return new AccommodationDetailPriceResponse(71466, 18, 1322396, 4, 55948, 25996, 8188, 819,
			1488195);
	}

	public AccommodationSearchResponse findAccommodations(
		AccommodationSearchRequest accommodationSearchRequest) {
		return new AccommodationSearchResponse(2,
			List.of(
				new AccommodationResponse(1L, "숙소 이름", "https://s3-alpha-sig.figma.com/img/6922/44b3/5c34d655bb2bab8f252f8742ee10b2c0?Expires=1655078400&Signature=fhXsW20hxN0uoDN899YKaWakMnhf6R0wzpatwbUNXYTo02mrK518vP7kNRE5P8rxN6-3Tawww7NQTFfB1cTU~cZRsR2Gfeuj~6oyEQIYEqERdjIKd6raeQaDR19bml-qUo3jDBGH2EubyzWtDeM0wMfPnaHOrG-XZOh1UGvWM921EuNI9b8AZ4CJ6KEE71SS7iq08HyyQscmkmrM2-c89HNqBH7keKXwfozKU6m2nMYiBe427qPxqP2IeKr0yU6K81fMHHuVM2gai5NKJ5vGpis74Omt4JcgQ1o5GuFfa9NbcBlAiI0rpeR2-aq1swK4gLuQp6IEdTJzmglpFSQy8Q__&Key-Pair-Id=APKAINTVSUGEWH5XD5UA", 4.80, 127, 82953, 1493159, false, new Point(107.21512421, 32.12332561)),
				new AccommodationResponse(2L, "숙소 이름 2", "https://s3-alpha-sig.figma.com/img/8316/2d4d/96d08957d6dfd6d333ba85f1dbf37478?Expires=1655078400&Signature=BpfyP25F6AAnJP-N9m9MnY1nZlskWpqAqLvYMzdOhacHDcG30XXqBfmrQhkchAXU~Q1oUY3xvd-IOr2759zsvKLPrvMNiT-1xV9fnyNw07b~2ylpxCAfQgY5JQVSvZ-TE6bFj-47xrQjsBG2spcmX-MJHe3CX3eM6gr0XS-qPagLBfdQXm9TgNQg7GQrumfjm-sOnl0eHqMwcvleLZ-tRiNZsfttHKQXN1HKuc3Y50iDnUgwz-5jNaUi8ohcoDBT6rMr5dR6RPN4tn4pUNYnMFr~eTSjm2REN9koMPf9n1G6HkDT9an1THCpWyc5isntxQRZFtZDfDfwlT6cBQXMcQ__&Key-Pair-Id=APKAINTVSUGEWH5XD5UA", 4.35, 105, 333, 1333332, true, new Point(107.33333321, 32.33256112))));
	}
}
