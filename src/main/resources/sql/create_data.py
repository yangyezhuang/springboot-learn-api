from faker import Faker
import csv

faker = Faker('zh_CN')

with open("test.csv","w",newline="") as csvfile:
    writer = csv.writer(csvfile)
    writer.writerow(["username","passwd","phone","email"])

    for i in range(20):
        info = []
        username = faker.user_name()
        passwd = faker.password(length=6, special_chars=False)
        phone = faker.phone_number()
        email = faker.ascii_free_email()
        info.append(username)
        info.append(passwd)
        info.append(phone)
        info.append(email)

        writer.writerow(info,)


