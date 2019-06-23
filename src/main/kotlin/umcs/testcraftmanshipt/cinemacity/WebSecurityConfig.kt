package umcs.testcraftmanshipt.cinemacity

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.provisioning.InMemoryUserDetailsManager
import org.springframework.util.DigestUtils

@EnableWebSecurity
@Configuration
class WebSecurityConfig : WebSecurityConfigurerAdapter() {
    override fun configure(http: HttpSecurity) {

        http.authorizeRequests()
                .antMatchers("/").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .permitAll()
                .and()
                .logout()
                .permitAll()
    }

    @Bean
    override fun userDetailsService(): UserDetailsService {
        val admin = User
                .withUsername("vladyslav")
                .roles("ADMIN")
                .password("admin")
                .passwordEncoder { password -> DigestUtils.md5DigestAsHex(password.toByteArray()) }
                .build()

        val cashier = User.withUsername("cashier")
                .roles("CASHIER")
                .password("cashier")
                .passwordEncoder { password -> DigestUtils.md5DigestAsHex(password.toByteArray()) }
                .build()

        return InMemoryUserDetailsManager(admin, cashier)

    }
}
