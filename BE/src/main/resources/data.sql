INSERT INTO accommodation
    (id, accommodation_fee, check_in_time, check_out_time, cleaning_fee, country, accommodation_description, host_image_url, host_name, accommodation_name, point, price, rating, review_count, bathroom_count, bedroom_count, max_guest_count, room_type, service_fee)
VALUES
    (1, 819, '16:00', '12:00', 25996, '서초구, 서울, 한국', '강남역 5번 출구에서 도보로 이동가능합니다 지하철, 버스 노선이 다양하고 맛집, 마트등 주변 시설이 풍부합니다. 깨끗하고, 아늑한 시설',
     'https://user-images.githubusercontent.com/92966772/171337583-5428b133-eea4-4f02-9f27-7a53a414842f.png', 'Jong',
     'Spacious and Comfortable cozy house #1', null, 71466, 4.8, 127, 1, 1, 3, 'PENSION' ,8188);

INSERT INTO accommodation
    (accommodation_name, accommodation_description, country, price, accommodation_fee, cleaning_fee, rating, review_count, bathroom_count, bedroom_count, max_guest_count, service_fee)
VALUES
    ('숙소 이름1', '숙소1의 상세설명', '서울시 서초구 양재동', '50000', '10000', '5000', '4.80', '40', '1', '1', '4', '500'),
    ('숙소 이름2', '숙소2의 상세설명', '서울시 서초구 양재동', '30000', '10000', '5000', '4.80', '40', '1', '1', '4', '500'),
    ('숙소 이름3', '숙소3의 상세설명', '서울시 서초구 양재동', '20000', '10000', '5000', '4.80', '40', '1', '1', '4', '500'),
    ('숙소 이름4 except 양재', '숙소4의 상세설명', '서울시 종로구 부암동', '50000', '10000', '5000', '4.80', '40', '1', '1', '4', '500');

INSERT INTO accommodation_image
    (id, accommodation_id, image_url)
VALUES
    (1, 1, 'https://s3-alpha-sig.figma.com/img/2b24/5101/2265996f97a70986d95f1d6fdd40009f?Expires=1655078400&Signature=D2aMRMQGvcfCORWvl9jrqgr6Rubf2044ovniztRQgTreOhAr8yzz~xQ7m0FHd1NC9DD0Z1C69F8XAODD44kfW9NDc~EHKhjdNzgBdgWZnYZRnB41avlSb2auh6ynKlaKD8yPs-St-sgrNy9QANbQguoDmDLixQXMRI1osY8zYNc4QPtUonohHD5Td71wqsLzMAWqSWsqWq5YquBmVG8iZ3nc9KjiujFSWhotD7bt0SpwoUl59w0WeuO~G~sSU7ns1FexmtPW8KYL~UIyiVXGGux23EqwOIIOZJRBWXkoSasdpczl0l4gwaXHDkpbFWSDE7lmc23B7waBAsF2I9SraA__&Key-Pair-Id=APKAINTVSUGEWH5XD5UA');

INSERT INTO wish (accommodation_id, is_deleted) VALUES (1, false);

INSERT INTO book
    (id, is_canceled, check_in, check_out, final_price, guest_count, accommodation_id)
VALUES
    (1, false, '2022-3-5-16-00', '2022-3-11-12-00', 393793, 2, 1);
