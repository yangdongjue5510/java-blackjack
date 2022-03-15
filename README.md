# java-blackjack

블랙잭 미션 저장소

## 우아한테크코스 코드리뷰

- [온라인 코드 리뷰 과정](https://github.com/woowacourse/woowacourse-docs/blob/master/maincourse/README.md)

## 요구사항 분석
- [x] 참가자의 이름을 입력받을 수 있다. -> 뷰
  - [x] 사용자의 이름은 쉼표(,) 기준으로 분리해서 입력받는다. -> 뷰
  - [x] 빈값, null, 딜러 라는 이름은 입력 불가.
- [ ] 각 참가자의 베팅 금액을 입력받을 수 있다.
  - [x] 베팅 금액은 음수가 될 수 없다.
  - [ ] 베팅 금액은 반드시 숫자여야 한다.
- [ ] 플레이어는 베팅 금액을 가지고 있는다.
- [x] 딜러를 포함한 모든 참가자에게 모두 두 장씩 나눠준다.
- [x] 딜러는 첫번째 카드만 보여준다.
- [x] 딜러 외 모든 참가자는 처음 받은 카드 모두 보여준다.
- [ ] 첫 카드 두장이 모두 블랙잭인 플레이어들은 딜러가 블랙잭이 아니면 1.5배를 받는다.
  - [ ] 딜러도 블랙잭이면 베팅한 금액을 다시 돌려놓는다.
- [x] 딜러를 제외한 플레이어들은 카드를 더 받을 지 여부를 결정할 수 있다. *
  - [x] 카드를 받기로 했으면, 한장 더 받는다.
    - [x] 카드를 받을 때마다 모든 카드를 출력한다.
  - [x] 카드를 받지 않기로 했으면, 다음 플레이어에게 넘어간다.
    - [x] 처음부터 카드를 받지 않는 경우는 모든 카드를 출력한다.
    - [x] 카드를 받은 적 있는 경우는 카드를 출력하지 않는다.
- [x] 딜러는 카드의 합이 16 이하면 카드 한장 더 받는다.
- [x] 모든 참가자의 이름, 카드, 합계를 보여준다.
- [ ] 최종 수익은 참가자들의 수익금액을 보여준다
  - [ ] 플레이어가 승리하면 베팅 금액만큼 얻는다.
  - [ ] 플레이어가 패배하면 베팅 금액만큼 잃는다.
  - [ ] 플레이어가 블랙잭으로 승리하면 베팅 금액의 1.5배만큼 얻는다.
  - [ ] 딜러가 21이 넘으면 플레이어 카드패 상관없이 승리한다.
  - [ ] 서로 블랙잭이면 낸 금액을 돌려받는다.

## 객체 목록
- 참가자
  - 이름
  - 보유하고 있는 카드
- 딜러
  - 보유하고 있는 카드
- 카드
  - 카드 타입(다이아, 하트 등)
  - 카드 내용?
    - 카드 내용이 A면 1 또는 11 로 할 수 있다.
    - K, Q, J 세가지는 10으로 가정한다.
- 보유하고 있는 카드 Cards(List<Card>)

모든 엔티티를 작게 유지한다.
3개 이상의 인스턴스 변수를 가진 클래스를 쓰지 않는다.
딜러와 플레이어에서 발생하는 중복 코드를 제거해야 한다.

## 도메인
- [x] 참가자 생성 (이름을 받아서)
- [x] 딜러 생성
- [x] 섞인 카드 덱을 준비한다.
- [x] 카드덱이 각 플레이어에게 두장씩 카드를 나눠준다.
- [x] 칙촉 차례가 되면, 칙촉이 보유한 카드가 총 합계를 계산한다.
  - [x] 21이 되거나 넘으면 카드를 더 받지 않는다. -> 보유카드가 하면 될 듯
  - [x] **사용자의 입력에 따라 카드를 더 받는다.**
  - [x] 카드를 더 받게 되면, 카드덱(제너레이터)가 참가자에게 카드를 준다.
  - [x] 참가자가 카드를 받으면 보유하고 있는 카드에 전달해준다.
- [x] 딜러 차례가 되면, 딜러의 보유한 카드가 합계를 계산한다.
  - [x] 21이 되거나 넘으면 카드를 더 받지 않는다. -> 보유카드가 하면 될 듯
  - [x] **16이하면 카드를 더 받는다.**
  - [x] 카드를 더 받게 되면, 카드덱(제너레이터)가 참가자에게 카드를 준다.
- [x] 딜러.보유한카드.합계 와 참가자.보유한카드.합계를 비교해서 결과 객체에 넣는다.

## 승부 규칙 (카드 갯수와 카드 합으로 계산.)
1. 카드가 두장이고 카드의 합이 21이면 블랙잭(가장 강함)
2. 카드가 두장 초과하면 카드 합이 21 이하면 카드 합이 큰 사람이 승리
3. 카드 갯수 상관없이 카드 합이 21 초과면 가장 약함.
4. 딜러와 플레이어 모두 bust면 플레이어가 승리한다.
5. 둘다 블랙잭이면 비긴다.
6. 둘다 블랙잭도 아니고 21초과도 아닌데 카드합이 같으면 비긴다

## 질문 할 것들
1. 플레이어와 딜러가 베팅 금액을 주고 받을 때 long 자료형을 사용하고 있어요.
   이걸 Money로 감싸서 전달할까 고민했는데 다른 객체로 감싼다고 뭐가 더 좋아질지 모르겠어서 그냥 long으로 전달하도록 했어요.
   오히려 모든 원시형을 다 감싸다보면 오히려 값을 꺼내는 과정이 더해지면서 코드가 더 길어지고 사용하기 어려워지지 않나? 생각이드는데,
   막상 코드 요구사항은 원시 자료형은 모두 감싸라고 해서 고민이에요.