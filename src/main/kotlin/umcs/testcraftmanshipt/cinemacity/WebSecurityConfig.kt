package umcs.testcraftmanshipt.cinemacity

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.provisioning.InMemoryUserDetailsManager

@EnableWebSecurity
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
class WebSecurityConfig : WebSecurityConfigurerAdapter() {
    override fun configure(http: HttpSecurity) {

        http.authorizeRequests()
                .antMatchers("/").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .permitAll()
                .and()
                .logout()
                .permitAll()
                .and()
                .cors().disable()
                .csrf().disable()
                .httpBasic()
    }

    @Bean
    override fun userDetailsService(): UserDetailsService {
        val admin = User
                .withDefaultPasswordEncoder()
                .username("vladyslav")
                .roles("ADMIN")
                .password("admin")
                .build()

        val cashier = User.withDefaultPasswordEncoder()
                .username("cashier")
                .roles("CASHIER")
                .password("cashier")
                .build()

        return InMemoryUserDetailsManager(admin, cashier)
    }
}
