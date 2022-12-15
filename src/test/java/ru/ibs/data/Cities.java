package ru.ibs.data;

public enum Cities {

    MOSCOW("Москва", "ул. Складочная, д. 3, стр. 1"),
    SPB("Санкт-Петербург", "Английская набережная, д. 70"),
    BARNAUL("Барнаул", "ул. Взлетная, д.33"),
    BELGOROD("Белгород", "проспект Богдана Хмельницкого, д. 111, каб. 530, 532"),
    VOLOGDA("Вологда", "ул. Зосимовская, д.18"),
    NZHNNVGD("Нижний Новгород", "ул. Максима Горького, д. 117,"),
    NVCHSK("Новочеркасск", "ул. Московская, д. 56"),
    OMSK("Омск", "ул. Маяковского, 74А"),
    PENZA("Пенза", "ул. Володарского, стр. 58, этаж 3, помещения 1-11"),
    PERM("Пермь", "ул. Куйбышева, 95б, 6 этаж"),
    TGNRK("Таганрог", "Гоголевский пер., 6"),
    ULNVSK("Ульяновск", "переулок Комсомольский, 22,"),
    UFA("Уфа", "ул. Менделеева, д. 130, блок А, 2 этаж, офис 201");

    private final String cityName, cityAddress;

    Cities(String cityName, String cityAddress) {
        this.cityName = cityName;
        this.cityAddress = cityAddress;
    }

    public String getCityName() {
        return cityName;
    }

    public String getCityAddress() {
        return cityAddress;
    }


}
