package com.hallak.PollRepositoryService.services;


import com.hallak.PollRepositoryService.dtos.BallotDTO;
import com.hallak.PollRepositoryService.dtos.PersonDTO;
import com.hallak.PollRepositoryService.dtos.PollDTO;
import com.hallak.PollRepositoryService.entities.Person;
import com.hallak.PollRepositoryService.entities.Poll;
import com.hallak.PollRepositoryService.repositories.BallotRepository;
import com.hallak.PollRepositoryService.repositories.PersonRepository;
import com.hallak.PollRepositoryService.repositories.PollRepository;
import jakarta.persistence.EntityExistsException;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class PollConsumerServiceImpl implements PollConsumerService {


    private final ModelMapper modelMapper;
    private final PollRepository pollRepository;
    private final PersonRepository personRepository;
    private final BallotRepository ballotRepository;
    private static final Logger log = LoggerFactory.getLogger(PollConsumerServiceImpl.class);

    @Autowired
    public PollConsumerServiceImpl(PersonService personService, BallotRepository ballotRepository, PersonRepository personRepository, ModelMapper modelMapper, PollRepository pollRepository) {
        this.modelMapper = modelMapper;
        this.pollRepository = pollRepository;
        this.ballotRepository = ballotRepository;
        this.personRepository = personRepository;
    }

    @Override
    @RabbitListener(queues = "${rabbitmq.queues.polls}")
    @Transactional
    public void receivePoll(@Payload PollDTO pollDTO){
        System.out.println("Recebido " + pollDTO);
        pollRepository.save(modelMapper.map(pollDTO, Poll.class));
    }

    @Override
    public void receiveBallot(BallotDTO ballotDTO) {
        return;
    }

    @Override
    @Transactional
    @RabbitListener(queues = "${rabbitmq.queues.persons}")
    public void receivePerson(@Payload PersonDTO personDTO){
        System.out.println("Recebido " + personDTO);

        boolean exists = personRepository.findByCpf(personDTO.cpf()).isPresent();
        if (exists) {
            throw new EntityExistsException("This person already exists");
        }

        personRepository.save(new Person(personDTO.cpf()));
    }






    /*@Override
    @RabbitListener(queues = "${rabbitmq.queues.ballots}")
    @Transactional
    public void receiveBallot(@Payload BallotDTO ballotDTO) {
        log.info("Recebido {}", ballotDTO);

        pollRepository.findById(ballotDTO.getPollId())
            .orElseThrow(() -> new EntityNotFoundException("Poll with ID " + ballotDTO.getPollId() + " not found"));



        Person person = personRepository.findByCpf(ballotDTO.getCpf()).orElse(null);
        if (person == null) {
            try {
                Person toCreate = new Person(ballotDTO.getPollId(), ballotDTO.getCpf());
                person = personService.insertPerson(toCreate);
            } catch (DataIntegrityViolationException | ObjectOptimisticLockingFailureException ex) {
                log.debug("Inserção concorrente detectada; reconsulta em seguida.", ex);
                person = personRepository.findByCpf(ballotDTO.getCpf())
                        .orElseThrow(() -> new IllegalStateException("Não encontrou Person após conflito", ex));
            }
        }
        Long personId = person.getId();


        boolean alreadyVoted = ballotRepository.existsByVoterIdAndPollId(personId, ballotDTO.getPollId());
        if (alreadyVoted) {
            log.warn("Voto duplicado ignorado. cpf={} pollId={}", ballotDTO.getCpf(), ballotDTO.getPollId());
            return;
        }


        boolean validOption = pollRepository.existsOptionInPoll(ballotDTO.getPollId(), ballotDTO.getOption());
        if (!validOption) {
            log.error("Opção inválida '{}' para poll {}", ballotDTO.getOption(), ballotDTO.getPollId());
            return;
        }


        Ballot ballot = new Ballot();
        ballot.setPollId(ballotDTO.getPollId());
        ballot.setVoterId(personId);
        ballot.setOption(ballotDTO.getOption());

        try {
            ballotRepository.saveAndFlush(ballot);
        } catch (DataIntegrityViolationException ex) {
            log.warn("Corrida detectada ao salvar ballot para personId={} pollId={} — ignorando.", personId, ballotDTO.getPollId(), ex);
        }
    }


*/


}