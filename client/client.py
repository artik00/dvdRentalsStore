import sys
import requests
from requests.exceptions import HTTPError


ENDPOINT = 'dvdrentals/'

CUSTOMERS_URL = 'customers'

FILM_URL = 'films'

#max defined as 100 in spring, change spring if you want to change this to more than 100
PAGE_SIZE = 90

def test_get_customer_and_rentals(hostname, port):
    print("Lets Query for all the customers one by one")
    for i in range(1, 601):
        try:
            get_customer(i, hostname, port)
        except HTTPError as ex:
            if i == 600:
                print('There is no customer with id 600, good behaviour')
            else:
                raise ex

def test_get_all_customers_with_paging(hostname, port):
    print("Lets Query for all the customers")
    page = 0
    total_pages = 1
    while page < total_pages:
        url = 'http://' + hostname + ':' + str(port) + '/' + ENDPOINT + CUSTOMERS_URL + '?size='+ \
              str(PAGE_SIZE) +'&page=' + str(page)
        response = requests.get(url)
        print(response.json())
        total_pages = response.json().get('totalPages')
        page += 1

def test_get_all_films(hostname, port):
    print("Lets Query for all the films")
    page = 0
    total_pages = 1
    while page < total_pages:
        url = 'http://' + hostname + ':' + str(port) + '/' + ENDPOINT + FILM_URL + '?size='+ \
              str(PAGE_SIZE) +'&page=' + str(page) + '&sort=_id'
        response = requests.get(url)
        print(response.json())
        total_pages = response.json().get('totalPages')
        page += 1

def get_customer(i, hostname, port):
    url = 'http://' + hostname + ':' + str(port) + '/' + ENDPOINT + CUSTOMERS_URL + '/' + str(i)
    response = requests.get(url)
    response.raise_for_status()
    print(response.json())
    rentals_url = 'http://' + hostname + ':' + str(port) + '/' + ENDPOINT + CUSTOMERS_URL + '/' + str(i) + '/rentals'
    response = requests.get(rentals_url)
    response.raise_for_status()
    print(response.json())

def test_get_film(hostname, port):
    print("Lets Query for all the films one by one")
    for i in range(1, 1002):
        try:
            get_film(i, hostname, port)
        except HTTPError as ex:
            if i == 1001:
                print('There is no film with id 1000, good behaviour')
            else:
                raise ex

def get_film(i, hostname, port):
    url = 'http://' + hostname + ':' + str(port) + '/' + ENDPOINT + FILM_URL + '/' + str(i)
    response = requests.get(url)
    response.raise_for_status()
    print(response.json())

if __name__ == '__main__':
    args = list(sys.argv[1:])
    hostname = args[0]
    port = args[1]
    test_get_customer_and_rentals(hostname, port)
    test_get_all_customers_with_paging(hostname, port)
    test_get_all_films(hostname, port)
    test_get_film(hostname, port)