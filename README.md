# 키친포스

## 미션 요구 사항

### 1단계 - 테스트를 통한 코드 보호

- [x] 키친포스의 코드를 보고 요구사항을 파악하여 작성
- [x] 요구사항을 기반으로 테스트 코드를 작성
    - [x] 모든 Business Object에 대해 테스트 코드 작성

### 2단계 - 서비스 리팩터링

- [ ] setter 제거하기
- [ ] 단위 테스트하기 어려운 코드와 단위 테스트 가능한 코드를 분리해 단위 테스트 가능한 코드에 대해 단위 테스트를 구현

## 요구사항

**MenuGroup**

- 메뉴그룹을 생성한다.
- 메뉴그룹 목록을 조회한다.

**Menu**

- 메뉴를 생성한다.
    - `예외`
    - 가격을 null일 수 없다.
    - 가격은 0보다 작을 수 없다.
    - 존재하지 않는 메뉴그룹으로 메뉴를 생성할 수 없다.
    - 존재하지 않는 상품의 id로 상품을 조회할 수 없다.
    - 메뉴의 가격은 상품들의 가격의 합보다 클 수 없다.
- 메뉴 목록을 조회한다.

**Order**

- 주문을 생성한다.
    - 주문 정보를 저장하여 주문을 저장한다.
    - `예외`
    - 비어있는 주문 정보를 통해 주문을 생성할 수 없다.
    - 메뉴의 수량과 주문 정보 수량과 다를 수 없다.
    - 존재하지 않는 주문 테이블을 조회할 수 없다.
    - 주문 테이블은 비어있을 수 없다.
- 주문 목록을 조회한다.
- 주문 상태를 변경한다.
    - 조회한 주문의 주문 정보(OrderLineItems)를 함께 저장한다.
    - `예외`
    - 존재하지 않는 주문 ID로 조회할 수 없다.
    - 주문의 상태가 COMPLETION일 경우 상태를 변경할 수 없다.

**Product**

- 상품을 생성한다.
    - `예외`
    - 가격은 null이 될 수 없다..
    - 가격은 0보다 작을 수 없다.
- 상품을 조회한다.

**TableGroup**

- 단체 지정을 생성한다.
    - 현재 날짜를 저장하여 단체 지정을 생성한다.
    - `예외`
    - 주문 테이블이 비어있을 수 없다.
    - 주문 테이블의 크기는 2보다 작을 수 없다.
    - 요청한 주문 테이블과 실제 저장된 주문 테이블은 같아야한다.
    - 저장된 주문 테이블은 비어있을 수 없다.
    - 저장된 주문 테이블의 id는 Null일 수 없다.
- 그룹을 해제한다.
    - 주문테이블에서 단체 지정을 검색하여 해제한다.
    - `예외`
    - 주문 상태가 COOKING이거나 MEAL인 주문일 때 해제할 수 없다.
    - 존재하지 않는 주문을 해제할 수 없다.

## 용어 사전

| 한글명 | 영문명 | 설명 |
| --- | --- | --- |
| 상품 | product | 메뉴를 관리하는 기준이 되는 데이터 |
| 메뉴 그룹 | menu group | 메뉴 묶음, 분류 |
| 메뉴 | menu | 메뉴 그룹에 속하는 실제 주문 가능 단위 |
| 메뉴 상품 | menu product | 메뉴에 속하는 수량이 있는 상품 |
| 금액 | amount | 가격 * 수량 |
| 주문 테이블 | order table | 매장에서 주문이 발생하는 영역 |
| 빈 테이블 | empty table | 주문을 등록할 수 없는 주문 테이블 |
| 주문 | order | 매장에서 발생하는 주문 |
| 주문 상태 | order status | 주문은 조리 ➜ 식사 ➜ 계산 완료 순서로 진행된다. |
| 방문한 손님 수 | number of guests | 필수 사항은 아니며 주문은 0명으로 등록할 수 있다. |
| 단체 지정 | table group | 통합 계산을 위해 개별 주문 테이블을 그룹화하는 기능 |
| 주문 항목 | order line item | 주문에 속하는 수량이 있는 메뉴 |
| 매장 식사 | eat in | 포장하지 않고 매장에서 식사하는 것 |

## 레거시 코드

### 빈약한 도메인 모델

- 객체지향의 Object는 state, behavior로 구성
- 하지만, 상태값만 가지고 있는 데이터홀더의 오브젝트로 사용된다.
    - 과도한 서비스 레이어의 사용을 부추긴다.

### 비즈니스 객체

- 비즈니스가 분산되어 구현된 객체
    - 동작을 실행하거나 관리
    - 클라이언트 데이터에 대한 요청을 DAO에 호출

### Big 서비스 레이어

- 도메인 로직을 여러곳에 산재하게 만들어 코드의 중복, 객체의 재활용성을 낮춘다.
