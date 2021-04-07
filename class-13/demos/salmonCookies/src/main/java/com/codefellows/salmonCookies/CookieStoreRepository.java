package com.codefellows.salmonCookies;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CookieStoreRepository extends JpaRepository<CookieStore, Long> {
//    This interface will load methods like save, update, delete, getone, getall from jpa
}
