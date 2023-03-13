# java-blackjack

블랙잭 미션 저장소

## 우아한테크코스 코드리뷰

- [온라인 코드 리뷰 과정](https://github.com/woowacourse/woowacourse-docs/blob/master/maincourse/README.md)

### 프로그램 동작 순서
- [x] 참가자 이름을 입력한다.
- [x] 참가자들의 배팅 금액을 입력한다.
- [x] 딜러와 참가자들에게 카드 2장을 나누어 준다.
- [x] 딜러와 참가자의 카드를 공개한다.
  - [x] 딜러는 한 장의 카드만 공개한다.
  - [x] 참가자는 두 장 모두 공개한다.
- [x] 참가자들은 조건부로 카드를 한 장씩 계속 받을 수 있다.
    - [x] 참가자가 카드를 받길 원하는 경우 카드를 주며 참가자의 모든 카드를 공개한다. (히트)
    - [x] [카드를 더 이상 받을 수 없는 조건] 참가자의 카드의 합이 21이 넘어가면 다음 참가자로 넘어간다.
    - [x] [카드를 더 이상 받을 수 없는 조건] 참가자가 더 받지 않기를 원하는 경우 다음 참가자로 넘어간다.
- [x] 딜러는 조건부로 카드를 한 장씩 계속 받을 수 있다.
  - [x] 딜러의 카드의 합이 16 이하이면 카드를 한 장씩 받는다.
  - [x] [카드를 더 이상 받을 수 없는 조건] 딜러의 카드의 합이 17이 넘어가면 다음 동작으로 넘어간다.
- [x] 딜러와 참가자의 모든 카드와 점수를 공개한다.
- [x] 딜러와 참가자의 수익을 출력한다.

### 기능 세부 사항

#### 1. 카드와 관련된 기능
  - [x] 카드
    - [x] 카드는 모양과 글자를 가진다.
    - [x] 카드가 에이스인지 확인한다.
  - [x] 핸드
    - [x] 플레이어가 가지고 있는 카드들을 가진다.
    - [x] 핸드에 카드를 추가한다.
    - [x] 핸드에 있는 카드들의 점수를 계산한다.
    - [x] 핸드에 있는 에이스 카드의 수를 계산한다.
  - [x] 덱
    - [x] 게임을 진행할 때 참여자들에게 나누어줄 카드들을 가진다.
    - [x] 카드를 한 장 뽑는다.
  - [x] 모양
    - [x] 트럼프 카드에 존재하는 모양을 가진다.
  - [x] 글자
    - [x] 트럼프 카드에 존재하는 글자를 가진다.

#### 2. 게임 진행과 관련된 기능
  - [x] 베팅 금액
    - [x] [예외 처리] 베팅 금액은 1,000원 이상 1,000,000원 이하만 가능하다.
  - [x] 카드를 더 받을지에 대한 플레이어의 답변을 가진 기능
    - [x] 플레이어가 선택할 수 있는 답변을 가지고 있다.
    - [x] 플레이어가 YES를 답했는지 확인한다.
  - [x] 게임 결과를 계산하는 기능
    - [x] 딜러와 플레이어들을 가진다.
    - [x] 필요한 참여자의 배팅 결과를 확인한다.
  - [x] 베팅 금액 계산과 관련된 기능
    - [x] 블랙잭, 승리, 무승부, 패배를 가진다.
  - [x] 점수와 관련된 기능
    - [x] 특정 점수만큼 빼는 기능
    - [x] 히트인지 확인하는 기능
    - [x] 버스트인지 확인하는 기능
    - [x] 특정 점수보다 큰지 확인하는 기능
    - [x] 특정 점수가 같은지 확인하는 기능

#### 3. 참여자와 관련된 기능
  - [x] 이름
    - [x] [예외 처리] 이름은 '딜러'이거나, 영문자로 이루어져야 한다.
    - [x] [예외 처리] 이름의 길이는 2 이상 5 이하이다.
  - [x] 참여자
    - [x] 참여자는 이름과 핸드를 가진다.
    - [x] 핸드에 카드를 추가한다.
    - [x] 핸드에 있는 카드들의 점수를 계산한다.
    - [x] 핸드에 있는 카드들의 점수로 버스트가 되는지 확인한다.
  - [x] 딜러
    - [x] 딜러는 참여자이다.
    - [x] 한 장의 카드를 반환할 수 있다.
    - [x] 핸드에 있는 카드의 합이 16 이하인지 확인한다.
  - [x] 플레이어
    - [x] 플레이어는 참여자이다.
  - [x] 참여자들
    - [x] 딜러와 플레이어들을 가진다.
      - [x] [예외 처리] 플레이어들이 존재해야 한다.
      - [x] [예외 처리] 플레이어의 이름이 중복되면 안 된다.
