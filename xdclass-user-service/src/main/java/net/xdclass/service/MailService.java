package net.xdclass.service;

public interface MailService {

    /**
     * 发送邮箱
     *
     * @param to
     * @param subject
     * @param content
     */
    void sendMail(String to, String subject, String content);

}
