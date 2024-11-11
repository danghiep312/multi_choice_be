# Thành viên:
## Vũ Nguyễn Cường - B20DCCN108
## Nguyễn Anh Vũ - B20DCCN744
## Phạm Quốc Việt - B20DCCN731

# Quiz Lab: Hệ thống cho phép tạo, thực hiện các gói câu hỏi trắc nghiệm

## Case study
Trong bối cảnh của cuộc cách mạng công nghệ và sự phổ biến của học trực tuyến, trường Đại học X đang đối diện với một loạt các thách thức liên quan đến việc tổ chức và quản lý các bài kiểm tra và bài trắc nghiệm cho sinh viên. Truyền thống sử dụng giấy và bút để tổ chức các bài kiểm tra đã trở nên không hiệu quả và khó khăn trong việc đánh giá và theo dõi kết quả của sinh viên, đặc biệt trong bối cảnh mà số lượng sinh viên tham gia học trực tuyến ngày càng tăng.

Cùng với đó, sự xuất hiện của các cuộc khủng hoảng như đại dịch COVID-19 đã khiến việc học trực tuyến trở thành một yếu tố không thể thiếu. Đối với trường Đại học X, việc tổ chức các bài kiểm tra từ xa và đảm bảo tính công bằng và chính xác của quá trình đánh giá đã trở thành một ưu tiên hàng đầu.

## Báo cáo
[https://docs.google.com/document/d/1Lh_s1-i2l7jDWV4v-7OqXzC8DZPi17QyhZAYGu3lbaQ/edit](https://docs.google.com/document/d/11GqVEoKEJQJgxjjT7sXXLyFmm3d35pFYD4nSMRFXeHE/edit?usp=sharing)

## Các kịch bản
### Kịch bản 1: Giáo viên tạo bài kiểm tra:
- Giáo viên muốn tạo một bài kiểm tra về chủ đề "Toán học cơ bản" cho lớp học của mình.
- Giáo viên đăng nhập vào hệ thống Quiz Lab, chọn tạo bài kiểm tra mới, cấu hình cho bài kiểm tra, nhập file chứa danh sách các câu hỏi về các chủ đề khác nhau trong toán học, nhập file danh sách sinh viên có thể tham gia.
- Hệ thống trả lại đường dẫn/ QR code có thể tham gia bài kiểm tra và gửi thông báo đến những sinh viên có trong danh sách tham gia nếu có.

### Kịch bản 2: Sinh viên tham gia bài kiểm tra:
- Sinh viên nhận được bài kiểm tra từ đường dẫn/QR code do giáo viên gửi hoặc từ thông báo.
- Sinh viên tham gia vào bài kiểm tra và làm các câu hỏi được đưa ra và nộp bài.
- Hệ thống chấm điểm và trả ra kết quả cho sinh viên đồng thời gửi thông báo cho giáo viên tạo bài kiểm tra.

## OpenAPI 3.0

![image](https://github.com/jnp2018/midproj-108731744/assets/88195984/3e411483-c3f9-49da-a806-87d21544c01c)

## AsyncApi

![image](https://github.com/jnp2018/midproj-108731744/assets/88195984/078a48f8-4d24-4c02-a4e6-34445d15571b)


![2024-05-08_095205](https://github.com/jnp2018/midproj-108731744/assets/88195984/37c4ceea-4f94-4106-89f9-f4bf07f451b9)



## Triển khai
### FE: Do quá 100MB nên bọn em phải tách làm 2 repo
https://github.com/vunguyencuong/quiz.git

### Công nghệ sử dụng
- BE: Spring boot.
- FE: Flutter (Web, App)
- Container: Docker, Docker-compose
- Service discovery: Netflix Eureka.
- Database: Postgresql, Redis.
- Giao tiếp giữa các service: OpenFeign.
- Api gateway: Kong
- Message broker: Kafka
- Third-party: Telegram bot
- Web server: Nginx
- API Document: Swagger
- Cloud provider: Google Cloud
### System design
![System design dark](https://github.com/jnp2018/midproj-108731744/assets/78639062/603824d2-f1e2-4209-90c9-80b91a81bcce)

### Netflix Eureka dashboard

![image](https://github.com/jnp2018/midproj-108731744/assets/88195984/0ccf721e-81a3-48d8-a270-7f0b27c2b08b)

### VM Instance dashboard

![image](https://github.com/jnp2018/midproj-108731744/assets/88195984/105cea7f-f25d-4e70-bea7-d57cc8d1f47b)

### Kong manager
#### Các service

![image](https://github.com/jnp2018/midproj-108731744/assets/88195984/cefa6fc5-9340-4a27-84b0-da19bb2c1611)

#### Các route

![image](https://github.com/jnp2018/midproj-108731744/assets/88195984/89cdcbd8-2d35-48b8-91df-136771845f1d)

#### Các plugin

![image](https://github.com/jnp2018/midproj-108731744/assets/88195984/09b64aab-e731-42a9-9210-8ff2e94b4a61)

##### Jwt plugin được sử dụng để verify token

![image](https://github.com/jnp2018/midproj-108731744/assets/88195984/3effee0e-1a32-440f-9624-907586b9cd05)

##### kong-jwt2header được sử dụng để extract claims token

![image](https://github.com/jnp2018/midproj-108731744/assets/88195984/515089d2-afc8-4dd8-8ca8-e1c831ee843e)

##### Cors plugin để quản lý cors

![image](https://github.com/jnp2018/midproj-108731744/assets/88195984/63910cbe-4fab-44ea-ac4c-232008cf7e85)

##### Request transformer plugin để xoá các header thừa và đổi tên header

![image](https://github.com/jnp2018/midproj-108731744/assets/88195984/d9a327c4-efbb-4e57-b35b-1f870371a946)

### Upstream để thực hiện load balance

![image](https://github.com/jnp2018/midproj-108731744/assets/88195984/49f7f8c7-10ec-4531-a454-16ffb732de4b)

![image](https://github.com/jnp2018/midproj-108731744/assets/88195984/69eff61f-0692-4b74-835c-7e10a8f4915b)

### SSL Certificate

![image](https://github.com/jnp2018/midproj-108731744/assets/88195984/26e4a020-d057-42f6-9d90-05026570b659)

### SNIs

![image](https://github.com/jnp2018/midproj-108731744/assets/88195984/707d7d04-861b-4e57-b6fd-acb6cafa2d44)

## Kết quả 
### Web
#### Giao diện đăng nhập

![2024-05-08_101348](https://github.com/jnp2018/midproj-108731744/assets/88195984/0babdc81-98e0-4efc-b26f-5c2b5955b592)

#### Giao diện đăng ký

![2024-05-08_101515](https://github.com/jnp2018/midproj-108731744/assets/88195984/0ba0f3b3-c312-461d-aec2-205799191e08)

#### Giao diện trang chủ đối với giáo viên

![2024-05-08_102031](https://github.com/jnp2018/midproj-108731744/assets/88195984/c666cd59-d321-495b-a2e4-3b4d6fdf6f36)

#### Giao diện trang chủ đối với học sinh

![2024-05-08_103343](https://github.com/jnp2018/midproj-108731744/assets/88195984/80d50b2e-8b54-4329-bf93-75e29c36afb6)

#### Giao diện tạo quiz của giáo viên

![2024-05-08_102145](https://github.com/jnp2018/midproj-108731744/assets/88195984/967e6f85-adb4-4882-a748-3a8397002239)

#### Giao diện khi giáo viên chọn import file json

![2024-05-08_105904](https://github.com/jnp2018/midproj-108731744/assets/88195984/fb2c463e-cc54-4b53-b749-a5889943c99d)

#### Giao diện preview questions khi giáo viên import file questions

![2024-05-08_102611](https://github.com/jnp2018/midproj-108731744/assets/88195984/adb8d90a-39e0-44c9-8f94-ddab1957fe6c)

### Giao diện làm bài của học sinh

![2024-05-08_121148](https://github.com/jnp2018/midproj-108731744/assets/88195984/4ee0cfd4-e94c-4e54-8830-8641c52a1e1f)

### Giao diện hiển thị status khi học sinh submit

![2024-05-08_121206](https://github.com/jnp2018/midproj-108731744/assets/88195984/d71689e1-0516-4de3-bff5-cc28485485c7)

### Giao diện hiển thị kết quả bài kiểm tra của học sinh 

![2024-05-08_121211](https://github.com/jnp2018/midproj-108731744/assets/88195984/4234601f-b989-4c60-8733-c9e8f14893f8)

#### Thông báo gửi về telegram cho học sinh khi có bài kiểm tra và kết quả sau khi hoàn thành bài kiểm tra

![image](https://github.com/jnp2018/midproj-108731744/assets/88195984/e62997b6-2f35-43e0-9e14-22611a9a61bf)


### Mobile
#### Giao diện đăng nhập

![image](https://github.com/jnp2018/midproj-108731744/assets/88195984/cf5b6998-38d5-4ef1-adc6-cd2361295a0a)


#### Giao diện đăng ký

![image](https://github.com/jnp2018/midproj-108731744/assets/88195984/6c6d2741-d377-4c5f-960b-74d77fdb716e)


#### Giao diện trang chủ đối với giáo viên

![image](https://github.com/jnp2018/midproj-108731744/assets/88195984/b07918fa-e440-4f80-99bb-9c134a457590)


#### Giao diện trang chủ đối với học sinh

![image](https://github.com/jnp2018/midproj-108731744/assets/88195984/38ef24ee-d9eb-4da8-add5-1aaa1a014cfa)


#### Giao diện tạo quiz của giáo viên

![image](https://github.com/jnp2018/midproj-108731744/assets/88195984/1f312c24-8a15-43b1-ada0-05a3b0b45621)


#### Giao diện khi giáo viên chọn import file json

![image](https://github.com/jnp2018/midproj-108731744/assets/88195984/0134b04d-ab76-418b-9021-5a38ab62f4a9)

#### Giao diện preview questions khi giáo viên import file questions

![image](https://github.com/jnp2018/midproj-108731744/assets/88195984/f8c5a1df-26f4-43cb-9432-edaae58d8b7f)

### Giao diện làm bài của học sinh

![image](https://github.com/jnp2018/midproj-108731744/assets/88195984/4d15f5c6-15df-4b07-84e1-0a31a26e84cd)

### Giao diện hiển thị status khi học sinh submit

![image](https://github.com/jnp2018/midproj-108731744/assets/88195984/a191026f-3126-4fbf-a434-e79032c0aaa9)

### Giao diện hiển thị kết quả bài kiểm tra của học sinh 

![image](https://github.com/jnp2018/midproj-108731744/assets/88195984/639439c3-2636-4061-b2fa-201a505bef0e)

#### Thông báo gửi về telegram cho học sinh khi có bài kiểm tra và kết quả sau khi hoàn thành bài kiểm tra

![image](https://github.com/jnp2018/midproj-108731744/assets/88195984/e62997b6-2f35-43e0-9e14-22611a9a61bf)
