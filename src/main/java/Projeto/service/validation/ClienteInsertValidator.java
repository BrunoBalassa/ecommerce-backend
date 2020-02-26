package Projeto.service.validation;
import Projeto.domain.Cliente;
import Projeto.domain.enums.TipoCliente;
import Projeto.dto.ClienteNewDTO;
import Projeto.repositories.ClienteRepository;
import Projeto.resources.excepetion.FieldMessage;
import Projeto.service.validation.utils.BR;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

  public class ClienteInsertValidator implements ConstraintValidator<ClienteInsert, ClienteNewDTO> {
        @Autowired
        private ClienteRepository repository;

        @Override
        public void initialize(ClienteInsert ann) {
        }
        @Override
        public boolean isValid(ClienteNewDTO objDto, ConstraintValidatorContext context) {
            List<FieldMessage> list = new ArrayList<>();

            if(objDto.getTipo().equals(TipoCliente.PESSOAFISICA.getCod()) && !BR.isValidCPF(objDto.getCpfOuCnpj())){
                list.add(new FieldMessage("cpfOuCnpj", "CPF inválido"));
            }
            if(objDto.getTipo().equals(TipoCliente.PESSOAJURIDICA.getCod()) && !BR.isValidCNPJ(objDto.getCpfOuCnpj())){
                list.add(new FieldMessage("cpfOuCnpj", "CPF inválido"));
            }

            Cliente aux = repository.findByEmail(objDto.getEmail());

            if (aux != null){
                list.add(new FieldMessage("email", "Email já existente"));
            }
            for (FieldMessage e : list) {
                context.disableDefaultConstraintViolation();
                context.buildConstraintViolationWithTemplate(e.getMessage())
                        .addPropertyNode(e.getFieldName()).addConstraintViolation();
            }
            return list.isEmpty();
        }


}
