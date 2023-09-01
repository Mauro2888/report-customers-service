package report.service.reports.mapper;

import java.time.LocalDate;
import java.util.UUID;

public record CustomerViewModel(UUID id, String name, String address, String phoneNumber, LocalDate createdAt) {

    public static class Builder{
        private UUID id;
        private String name;
        private String address;
        private String phoneNumber;
        private LocalDate createdAt;


        public Builder withId(UUID id){
            this.id = id;
            return this;
        }

        public Builder withName(String name){
            this.name = name;
            return this;
        }

        public Builder withAddress(String address){
            this.address = address;
            return this;
        }

        public Builder withPhoneNumber(String phoneNumber){
            this.phoneNumber = phoneNumber;
            return this;
        }

        public Builder withCreatedAt(LocalDate createdAt){
            this.createdAt = createdAt;
            return this;
        }

        public CustomerViewModel build(){
            return new CustomerViewModel(id,name,address,phoneNumber,createdAt);
        }
    }

    public static Builder builder(){
        return new Builder();
    }
}
