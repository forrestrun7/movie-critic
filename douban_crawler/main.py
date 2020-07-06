import random
import re
import requests
from lxml import etree
import csv
import time
import os

headers = {
    'User-Agent': 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.116 Safari/537.36'}


# 爬取豆瓣top250主界面每个电影页面的url
def index_pages(number):
    url = 'https://movie.douban.com/top250?start=%s&filter=' % number
    index_response = requests.get(url=url, headers=headers)
    tree = etree.HTML(index_response.text)
    m_urls = tree.xpath("//li/div/div/a/@href")
    return m_urls


# 分析页面元素
def parse_pages(url):
    try:
        movie_pages = requests.get(url=url, headers=headers)
        parse_movie = etree.HTML(movie_pages.text)

        # 电影名
        m_name = parse_movie.xpath("//h1/span[1]/text()")[0].strip()
        temp = m_name.split()
        m_name = temp[0]

        # 又名
        value = re.findall('<span class="pl">又名:</span>(.*?)<br/>', movie_pages.text)
        other_name = [" ".join(['又名:'] + value)]

        # 导演
        value = parse_movie.xpath("//div[@id='info']/span[1]/span[@class='attrs']/a/text()")
        director = [" ".join(['导演:'] + value)]

        # 编剧
        value = parse_movie.xpath("//div[@id='info']/span[2]/span[@class='attrs']/a/text()")
        screenwriters = [" ".join(['编剧:'] + value)]

        # 主演
        value = parse_movie.xpath("//div[@id='info']/span[3]")
        performers = [value[0].xpath('string(.)')]

        # 类型
        value = parse_movie.xpath("//span[@property='v:genre']/text()")
        types = [" ".join(['类型：'] + value)]

        # 语言
        value = re.findall('<span class="pl">语言:</span>(.*?)<br/>', movie_pages.text)
        language = [" ".join(['语言:'] + value)]

        # 片长
        value = parse_movie.xpath("//span[@property='v:runtime']/text()")
        time = [" ".join(['片长：'] + value)]

        # 上映日期
        value = parse_movie.xpath("//span[@property='v:initialReleaseDate']/text()")
        date = [" ".join(['上映日期：'] + value)]

        # 制片国家/地区
        value = re.findall('<span class="pl">制片国家/地区:</span>(.*?)<br/>', movie_pages.text)
        country = [" ".join(['制片国家:'] + value)]

        poster = parse_movie.xpath("//div[@id='mainpic']/a/img/@src")
        response = requests.get(poster[0])
        name2 = m_name
        poster_name = name2 + '.jpg'
        dir_name = 'movie_poster'
        if not os.path.exists(dir_name):
            os.mkdir(dir_name)
        poster_path = dir_name + '/' + poster_name
        with open(poster_path, "wb")as f:
            f.write(response.content)
        # str转换成list，不转换存的数据只有字符串的第一个字符
        movie_name = [m_name]
        return zip(movie_name, other_name, director, screenwriters, performers, types, language, time, date, country)
    except:
        pass


def save_results(data):
    with open('movie.csv', 'a', encoding="utf-8-sig") as fp:
        writer = csv.writer(fp)
        writer.writerow(data)


if __name__ == '__main__':
    num = 0
    for i in range(0, 250, 25):
        movie_urls = index_pages(i)
        for movie_url in movie_urls:
            try:
                results = parse_pages(movie_url)
                for result in results:
                    num += 1
                    save_results(result)
                    print('第' + str(num) + '条电影信息保存完毕！')
                    # 随机等待一段时间，避免触发反爬机制
                    time.sleep(random.random() * 3)
            except:
                pass
