![header](https://capsule-render.vercel.app/api?type=slice&height=150&color=1a1b27&text=ManagementV2&reversal=false&fontSize=45&fontColor=628fda&textBg=false&fontAlign=77&section=header&fontAlignY=37)


## 프로젝트 제목
다국적 기업 관리를 위한 견적 및 주문 관리 프로그램

## 프로젝트 정보
> **제작 목적**

각기 다른 외화를 사용하고 있는 회사들에서 들어오는 견적 문의를 효과적으로 관리하며, 주문을 처리할 때 실시간 환율을 고려하여 각 회사의 이윤을 쉽게 확인하는 프로젝트를 제작하고자 합니다.

> **제작 기간**

2023.12.8 ~ 2024.3.27 [약 4달]

## 프로젝트 소개
> **기술 스택**
- VCS

<img src="https://img.shields.io/badge/github-181717?style=for-the-badge&logo=github&logoColor=white">

- Back End

<img src="https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white"> <img src="https://img.shields.io/badge/gradle-02303A?style=for-the-badge&logo=gradle&logoColor=white"> <img src="https://img.shields.io/badge/spring-6DB33F?style=for-the-badge&logo=spring&logoColor=white"> <img src="https://img.shields.io/badge/springboot-6DB33F?style=for-the-badge&logo=springboot&logoColor=white"> 

JPA & H2 DB(for test)

- Front End

<img src="https://img.shields.io/badge/javascript-F7DF1E?style=for-the-badge&logo=javascript&logoColor=black"> <img src="https://img.shields.io/badge/react-61DAFB?style=for-the-badge&logo=react&logoColor=black"> <img src="https://img.shields.io/badge/bootstrap-7952B3?style=for-the-badge&logo=bootstrap&logoColor=white"> 

> **기능**

>> **1. 실시간 환율 조회 기능**

- 한국 수출입은행이 제공하는 Open API를 활용하여 실시간으로 환율 정보를 가져와 메인 페이지에서 확인할 수 있도록 구현했습니다.

>> **2. 회사 관리 기능**

- 회사를 등록할 때 회사 이름, 사용하는 통화, 기본 할인율을 입력함으로써 각기 다른 외화를 사용하는 회사를 효율적으로 관리할 수 있도록 도와줍니다.

- 그 외에도 회사 정보를 수정하거나, 관리 대상이 아닌 회사에 대해서 삭제도 가능합니다.

>> **3. 견적 추가 기능**

- 각 회사별로 견적을 등록하거나 확인할 수 있습니다.

- 모든 제품들은 DB에서 관리되며, 견적 의뢰가 들어온 제품들의 고유번호(Article Num) 혹은 이름을 검색하여 손쉽게 제품을 찾을 수 있습니다.

- 견적 의뢰가 들어온 제품들을 추가하게 되면 해당 회사의 기본 할인율이 적용되어 수출가격이 산출됩니다.
이 때, 추가 할인이 필요한 경우에는 수출 가격 수정 기능을 통해 수정이 가능합니다.

- 최종적으로 저장하면 회사별 견적서에서 확인할 수 있습니다.

>> **4. 주문서 작성 기능**

- 견적에서 제공된 가격을 바탕으로 해당 제품의 주문 수량을 입력해 주문서를 작성합니다.

- 주문을 하면 주문 횟수가 기록되며, Open API 를 통해서 가져온 실시간 환율을 적용하여 해당 주문의 이익이 계산됩니다.

>> **5. 회사별 이윤 및 날짜별 총 이윤 확인 기능**

- 모든 날짜에 대한 회사별 이윤을 확인할 수 있습니다.

- 원하는 날짜를 선택하여 해당 날짜의 회사별 이윤과 그것들을 합친 그날의 총 이윤까지 확인할 수 있습니다.

